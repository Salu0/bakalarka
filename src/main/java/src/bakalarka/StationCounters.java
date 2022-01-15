package src.bakalarka;

import src.bakalarka.db.writers.DBWriter;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StationCounters {
    //Object {"status":"ok","msg-id":5,"data":{"serial":"725551","in":200.00,"out":600.00,"bet":0.00,"win":0.00,"game":0,"updated":"2018-10-24T11:20:13.536"}} was
    private Integer idStation;
    private String serialNumber;
    private BigDecimal inMultiplier;
    private BigDecimal outMultiplier;
    private BigDecimal betMultiplier;
    private BigDecimal winMultiplier;
    private BigDecimal gameMultiplier;
    private LocalDateTime lastStationResponse;

    public StationCounters(Integer idStation, String serialNumber, BigDecimal inMultiplier, BigDecimal outMultiplier, BigDecimal betMultiplier, BigDecimal winMultiplier, BigDecimal gameMultiplier, LocalDateTime lastStationResponse) {
        this.idStation = idStation;
        this.serialNumber = serialNumber;
        this.inMultiplier = inMultiplier;
        this.outMultiplier = outMultiplier;
        this.betMultiplier = betMultiplier;
        this.winMultiplier = winMultiplier;
        this.gameMultiplier = gameMultiplier;
        this.lastStationResponse = lastStationResponse;
    }

    public void updateQuery() {
        try {
            DBWriter.prepareSql("UPDATE counters.pg_station SET in_multiplier = " + inMultiplier + ", out_multiplier = " + outMultiplier + ", bet_multiplier = " + betMultiplier + ", win_multiplier = " + winMultiplier + ", game_multiplier = " + gameMultiplier + ", last_station_response = " + getLastStationResponse() + " WHERE IDstation = " + idStation + ';');
        } catch (SQLException e) {
            System.out.println("SQLException, Station counters");
        }
    }

    private String getLastStationResponse() {
        if (lastStationResponse == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(lastStationResponse.format(formatter));
    }

    private String getValidSQLString(String string) {
        if (string == null) { return null; }
        return "'" + string + "'";
    }

    public void getStationIdWithSerialNumber() {
        System.out.println(idStation + " " + serialNumber);
    }
}
