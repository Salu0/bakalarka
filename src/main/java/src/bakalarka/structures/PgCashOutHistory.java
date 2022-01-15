package src.bakalarka.structures;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PgCashOutHistory {

    private Integer id;
    private Integer idStation;
    private LocalDateTime dateTimeOfRecord;
    private BigDecimal cashOutAmount;
    private String comment;

    public PgCashOutHistory(Integer id, Integer idStation, LocalDateTime dateOfRecord, BigDecimal cashOutAmount, String comment) {
        this.id = id;
        this.idStation = idStation;
        this.dateTimeOfRecord = dateOfRecord;
        this.cashOutAmount = cashOutAmount;
        this.comment = comment;
    }

    public PgCashOutHistory(Integer idStation, LocalDateTime dateOfRecord, BigDecimal cashOutAmount, String comment) {
        this.idStation = idStation;
        this.dateTimeOfRecord = dateOfRecord;
        this.cashOutAmount = cashOutAmount;
        this.comment = comment;
    }

    public String insertQuerry() {
        return "insert into pg_cash_out_history (IDstation, date_of_record, cash_out_amount, comment)" +
                " values (" + idStation + ", " + getDateTimeOfRecordAsSQLValidString() + ", " + cashOutAmount + ", " + getValidSQLString(comment) + ");";
    }

    private String getDateTimeOfRecordAsSQLValidString() {
        if (dateTimeOfRecord == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(dateTimeOfRecord.format(formatter));
    }

    private String getValidSQLString(String string) {
        if (string == null) { return null; }
        return "'" + string + "'";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdStation() {
        return idStation;
    }

    public void setIdStation(Integer idStation) {
        this.idStation = idStation;
    }

    public String getDateTimeOfRecord() {
        if (dateTimeOfRecord == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeOfRecord.format(formatter);
    }

    public LocalDateTime getDateTimeOfRecordAsLocalDateTime() {
        return dateTimeOfRecord;
    }

    public void setDateTimeOfRecord(LocalDateTime dateTimeOfRecord) {
        this.dateTimeOfRecord = dateTimeOfRecord;
    }

    public BigDecimal getCashOutAmount() {
        return cashOutAmount;
    }

    public void setCashOutAmount(BigDecimal cashOutAmount) {
        this.cashOutAmount = cashOutAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
