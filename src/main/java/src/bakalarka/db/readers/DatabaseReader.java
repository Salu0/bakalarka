package src.bakalarka.db.readers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.bakalarka.ClientFactory;
import src.bakalarka.db.Db;
import src.bakalarka.structures.PgCashOutHistory;
import src.bakalarka.structures.PgSettlementMachine;
import src.bakalarka.structures.PgStation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;

public class DatabaseReader {

    public static ObservableList<PgStation> readDataFromPgStation(String query, ExecutorService threadPool) throws SQLException {
        ObservableList<PgStation> data = FXCollections.observableArrayList();
        try (Connection con = Db.getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    System.out.println("Asserting: " + query);
                    while (resultSet.next()) {
                        LocalDateTime dateTime = null;

                        int idStation = resultSet.getInt(1);
                        int idCashDesk = resultSet.getInt(2);
                        String name = resultSet.getString(3);
                        String serialNumber = resultSet.getString(4);
                        int deleted = resultSet.getInt(5);
                        BigDecimal currentIn = resultSet.getBigDecimal(6);
                        BigDecimal currentOut = resultSet.getBigDecimal(7);
                        BigDecimal currentBet = resultSet.getBigDecimal(8);
                        BigDecimal currentWin = resultSet.getBigDecimal(9);
                        BigDecimal currentGame = resultSet.getBigDecimal(10);
                        if (resultSet.getTimestamp(11) != null) { dateTime = resultSet.getTimestamp(11).toLocalDateTime(); }
                        String barName = resultSet.getString(12);

                        PgStation pgStation = new PgStation(idStation, idCashDesk, name, serialNumber, deleted, currentIn, currentOut, currentBet, currentWin, currentGame, dateTime, getInOutDifferenceOrNull(currentIn, currentOut), barName);
                        data.add(pgStation);
                    }
                    ClientFactory.splitStations(data, 10, threadPool);
                    return data;
                }
            }
        }
    }

    public static ObservableList<PgSettlementMachine> readDataFromPgSettlementMachine(String query) throws SQLException {
        ObservableList<PgSettlementMachine> data = FXCollections.observableArrayList();
        try (Connection con = Db.getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    System.out.println("Asserting: " + query);
                    while (resultSet.next()) {
                        LocalDateTime manualInserted = null;
                        LocalDate date = null;

                        int idRecord = resultSet.getInt(1);
                        int idPrevRecord = resultSet.getInt(2);
                        int idSettlement = resultSet.getInt(3);
                        int idStation = resultSet.getInt(4);
                        if (resultSet.getTimestamp(5) != null) { date = resultSet.getTimestamp(5).toLocalDateTime().toLocalDate(); }
                        BigDecimal currentIn = resultSet.getBigDecimal(6);
                        BigDecimal currentOut = resultSet.getBigDecimal(7);
                        BigDecimal currentBet = resultSet.getBigDecimal(8);
                        BigDecimal currentWin = resultSet.getBigDecimal(9);
                        BigDecimal currentGame = resultSet.getBigDecimal(10);
                        BigDecimal currentTicketIn = resultSet.getBigDecimal(11);
                        BigDecimal currentTicketOut = resultSet.getBigDecimal(12);
                        BigDecimal currentPromoIn = resultSet.getBigDecimal(13);
                        BigDecimal currentPromoOut = resultSet.getBigDecimal(14);
                        BigDecimal currentJackpotOut = resultSet.getBigDecimal(15);
                        BigDecimal oldIn = resultSet.getBigDecimal(16);
                        BigDecimal oldOut = resultSet.getBigDecimal(17);
                        BigDecimal newIn = resultSet.getBigDecimal(18);
                        BigDecimal newOut = resultSet.getBigDecimal(19);
                        BigDecimal totalIn = resultSet.getBigDecimal(20);
                        BigDecimal totalOut = resultSet.getBigDecimal(21);
                        BigDecimal totalTicketIn = resultSet.getBigDecimal(22);
                        BigDecimal totalTicketOut = resultSet.getBigDecimal(23);
                        BigDecimal totalPromoIn = resultSet.getBigDecimal(24);
                        BigDecimal totalPromoOut = resultSet.getBigDecimal(25);
                        BigDecimal totalJackpotOut = resultSet.getBigDecimal(26);
                        BigDecimal totalBet = resultSet.getBigDecimal(27);
                        BigDecimal totalWin = resultSet.getBigDecimal(28);
                        BigDecimal totalGame = resultSet.getBigDecimal(29);
                        BigDecimal payoff = resultSet.getBigDecimal(30);
                        BigDecimal missingPayoff = resultSet.getBigDecimal(31);
                        int manualEntry = resultSet.getInt(32);
                        if (resultSet.getTimestamp(33) != null) { manualInserted = resultSet.getTimestamp(33).toLocalDateTime(); }
                        int idUser = resultSet.getInt(34);

                        PgSettlementMachine pgSettlementMachine = new PgSettlementMachine(idRecord, idPrevRecord, idSettlement, idStation, date, currentIn, currentOut, currentBet, currentWin, currentGame, currentTicketIn, currentTicketOut, currentPromoIn, currentPromoOut, currentJackpotOut, oldIn, oldOut, newIn, newOut, totalIn, totalOut, totalTicketIn, totalTicketOut, totalPromoIn, totalPromoOut, totalJackpotOut, totalBet, totalWin, totalGame, payoff, missingPayoff, manualEntry, manualInserted, idUser);
                        data.add(pgSettlementMachine);
                    }
                    return data;
                }
            }
        }
    }

    public static ObservableList<PgCashOutHistory> readDataFromPgCashOutHistory(String query) throws SQLException {
        ObservableList<PgCashOutHistory> data = FXCollections.observableArrayList();
        try (Connection con = Db.getConnection()) {
            try (Statement statement = con.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    System.out.println("Asserting: " + query);
                    while (resultSet.next()) {
                        LocalDateTime dateOfRecord = null;

                        int id = resultSet.getInt(1);
                        int idStation = resultSet.getInt(2);
                        if (resultSet.getTimestamp(3) != null) { dateOfRecord = resultSet.getTimestamp(3).toLocalDateTime(); }
                        BigDecimal cashOutAmount = resultSet.getBigDecimal(4);
                        String comment = resultSet.getString(5);

                        PgCashOutHistory pgCashOutHistory = new PgCashOutHistory(id, idStation, dateOfRecord, cashOutAmount, comment);
                        data.add(pgCashOutHistory);
                    }
                    return data;
                }
            }
        }
    }

    private static BigDecimal getInOutDifferenceOrNull(BigDecimal lastIn, BigDecimal lastOut) {
        if (lastIn == null) {
            if (lastOut == null) {
                return null;
            }
            lastIn = BigDecimal.ZERO;
        }
        if (lastOut == null) {
            lastOut = BigDecimal.ZERO;
        }
        return lastIn.subtract(lastOut).equals(BigDecimal.ZERO) ? null : lastIn.subtract(lastOut);
    }
}
