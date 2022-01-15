package src.bakalarka.structures;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PgStation {

    @SerializedName("IDstation")
    private Integer idStation;
    @SerializedName("IDtype")
    private Integer idType;
    @SerializedName("IDcashDesk")
    private Integer idCashDesk;
    @SerializedName("IDstationGroup")
    private Integer idStationGroup;
    @SerializedName("IDwatcher")
    private Integer idWatcher;
    @SerializedName("extern_identifier")
    private String externIdentifier;
    @SerializedName("extern_identifier_desk")
    private Integer externIdentifierDesk;
    @SerializedName("extern_identifier_pi")
    private String externIdentifierPi;
    @SerializedName("fw_desk")
    private String fwDesk;
    private String name;
    private BigDecimal multiplier;
    @SerializedName("serial_number")
    private String serialNumber;
    private String note;
    @SerializedName("date_start")
    private LocalDateTime dateStart;
    @SerializedName("date_end")
    private LocalDateTime dateEnd;
    @SerializedName("last_action")
    private LocalDateTime lastAction;
    private Integer deleted;
    @SerializedName("submit_in")
    private Integer submitIn;
    private Integer sequence;
    @SerializedName("last_station_response")
    private LocalDateTime lastStationResponse;
    @SerializedName("last_not_final_response")
    private LocalDateTime lastNotFinalResponse;
    @SerializedName("last_game_response")
    private LocalDateTime lastGameResponse;
    @SerializedName("in_multiplier")
    private BigDecimal inMultiplier;
    @SerializedName("out_multiplier")
    private BigDecimal outMultiplier;
    @SerializedName("bet_multiplier")
    private BigDecimal betMultiplier;
    @SerializedName("win_multiplier")
    private BigDecimal winMultiplier;
    @SerializedName("game_multiplier")
    private BigDecimal gameMultiplier;
    private Integer filter;
    private Integer quiet;
    private Integer level;
    @SerializedName("date_last_in")
    private LocalDateTime dateLastIn;
    @SerializedName("last_in_value")
    private BigDecimal lastInValue;
    @SerializedName("date_last_out")
    private LocalDateTime dateLastOut;
    @SerializedName("last_out_value")
    private BigDecimal lastOutValue;
    @SerializedName("send_sms")
    private Integer sendSms;
    @SerializedName("is_button")
    private Integer isButton;
    @SerializedName("payoff_temp")
    private BigDecimal payoffTemp;
    @SerializedName("is_server_2")
    private Integer isServer2;
    private Integer router;
    private String ip;
    @SerializedName("temporary_blocked")
    private Integer temporaryBlocked;
    @SerializedName("last_router_contact")
    private LocalDateTime lastRouterContact;
    @SerializedName("connection_type")
    private String connectionType;
    private String currency;
    private String barName;
    private BigDecimal availableBalance;

    public PgStation(Integer idStation, Integer idCashDesk, String name, String serialNumber, Integer deleted, BigDecimal inMultiplier, BigDecimal outMultiplier, BigDecimal betMultiplier, BigDecimal winMultiplier, BigDecimal gameMultiplier, LocalDateTime dateLastIn, BigDecimal availableBalance, String barName) {
        this.idStation = idStation;
        this.idCashDesk = idCashDesk;
        this.name = name;
        this.serialNumber = serialNumber;
        this.deleted = deleted;
        this.inMultiplier = inMultiplier;
        this.outMultiplier = outMultiplier;
        this.betMultiplier = betMultiplier;
        this.winMultiplier = winMultiplier;
        this.gameMultiplier = gameMultiplier;
        this.dateLastIn = dateLastIn;
        this.availableBalance = availableBalance;
        this.barName = barName;
    }

    public PgStation(String serialNumber, BigDecimal inMultiplier, BigDecimal outMultiplier, BigDecimal betMultiplier, BigDecimal winMultiplier, BigDecimal gameMultiplier, LocalDateTime dateLastIn, Integer idStation) {
        this.serialNumber = serialNumber;
        this.inMultiplier = inMultiplier;
        this.outMultiplier = outMultiplier;
        this.betMultiplier = betMultiplier;
        this.winMultiplier = winMultiplier;
        this.gameMultiplier = gameMultiplier;
        this.dateLastIn = dateLastIn;
        this.idStation = idStation;
    }

    public JsonObject getLogin() {
        JsonObject loginJson = new JsonObject();
        loginJson.addProperty("login", "feed");
        loginJson.addProperty("serial", serialNumber);
        return loginJson;
    }

    public JsonObject getCounters() {
        JsonObject countersJson = new JsonObject();
        countersJson.addProperty("get", "counters");
        return countersJson;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    private String getDateStart() {
        if (dateStart == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(dateStart.format(formatter));
    }

    private String getValidSQLString(String string) {
        if (string == null) { return null; }
        return "'" + string + "'";
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setLastAction(LocalDateTime lastAction) {
        this.lastAction = lastAction;
    }

    public void setLastStationResponse(LocalDateTime lastStationResponse) {
        this.lastStationResponse = lastStationResponse;
    }

    public void setLastNotFinalResponse(LocalDateTime lastNotFinalResponse) {
        this.lastNotFinalResponse = lastNotFinalResponse;
    }

    public void setLastGameResponse(LocalDateTime lastGameResponse) {
        this.lastGameResponse = lastGameResponse;
    }

    public void setDateLastIn(LocalDateTime dateLastIn) {
        this.dateLastIn = dateLastIn;
    }

    public void setDateLastOut(LocalDateTime dateLastOut) {
        this.dateLastOut = dateLastOut;
    }

    public void setLastRouterContact(LocalDateTime lastRouterContact) {
        this.lastRouterContact = lastRouterContact;
    }

    public String insertQuery() {
        return "insert into pg_station (IDstation, IDtype, IDcashDesk, IDstationGroup, IDwatcher, extern_identifier, extern_identifier_desk, extern_identifier_pi, fw_desk, name, multiplier, serial_number, note, date_start, date_end, last_action, deleted, submit_in, sequence, last_station_response, last_not_final_response, last_game_response, in_multiplier, out_multiplier, bet_multiplier, win_multiplier, game_multiplier, filter, quiet, level, date_last_in, last_in_value, date_last_out, last_out_value, send_sms, is_button, payoff_temp, is_server_2, router, ip, temporary_blocked, last_router_contact, connection_type, currency)" +
                " values (" + idStation + ", " + idType + ", " + idCashDesk + ", " + idStationGroup + ", " + idWatcher + ", " + getValidSQLString(externIdentifier) + ", " + externIdentifierDesk + ", " + getValidSQLString(externIdentifierPi) + ", " + getValidSQLString(fwDesk) + ", " + getValidSQLString(name) + ", " + multiplier + ", " + getValidSQLString(serialNumber) + ", " + getValidSQLString(note) + ", " + getDateStart() + ", " + getDateEnd() + ", " + getLastAction() + ", " + deleted + ", " + submitIn + ", " + sequence + ", " + getLastStationResponse() + ", " + getLastNotFinalResponse() + ", " + getLastGameResponse() + ", " + inMultiplier + ", " + outMultiplier + ", " + betMultiplier + ", " + winMultiplier + ", " + gameMultiplier + ", " + filter + ", " + quiet + ", " + level + ", " + getValidSQLDateLastIn() + ", " + lastInValue + ", " + getDateLastOut() + ", " + lastOutValue + ", " + sendSms + ", " + isButton + ", " + payoffTemp + ", " + isServer2 + ", " + router + ", " + getValidSQLString(ip) + ", " + temporaryBlocked + ", " + getLastRouterContact() + ", " + getValidSQLString(connectionType) + ", " + getValidSQLString(currency) + ");";
    }

    private String getDateEnd() {
        if (dateEnd == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(dateEnd.format(formatter));
    }

    private String getLastAction() {
        if (lastAction == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(lastAction.format(formatter));
    }

    private String getLastStationResponse() {
        if (lastStationResponse == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(lastStationResponse.format(formatter));
    }

    private String getLastNotFinalResponse() {
        if (lastNotFinalResponse == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(lastNotFinalResponse.format(formatter));
    }

    private String getLastGameResponse() {
        if (lastGameResponse == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(lastGameResponse.format(formatter));
    }

    private String getValidSQLDateLastIn() {
        if (dateLastIn == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(dateLastIn.format(formatter));
    }

    public String getDateLastIn() {
        if (dateLastIn == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateLastIn.format(formatter);
    }

    public LocalDateTime getDateLastInAsLocalDateTime() {
        return dateLastIn;
    }

    private String getDateLastOut() {
        if (dateLastOut == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(dateLastOut.format(formatter));
    }

    private String getLastRouterContact() {
        if (lastRouterContact == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getValidSQLString(lastRouterContact.format(formatter));
    }

    public Integer getIdStation() {
        return idStation;
    }

    public Integer getIdType() {
        return idType;
    }

    public Integer getIdCashDesk() {
        return idCashDesk;
    }

    public Integer getIdStationGroup() {
        return idStationGroup;
    }

    public Integer getIdWatcher() {
        return idWatcher;
    }

    public String getExternIdentifier() {
        return externIdentifier;
    }

    public Integer getExternIdentifierDesk() {
        return externIdentifierDesk;
    }

    public String getExternIdentifierPi() {
        return externIdentifierPi;
    }

    public String getFwDesk() {
        return fwDesk;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getNote() {
        return note;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public Integer getSubmitIn() {
        return submitIn;
    }

    public Integer getSequence() {
        return sequence;
    }

    public BigDecimal getInMultiplier() {
        return inMultiplier;
    }

    public BigDecimal getOutMultiplier() {
        return outMultiplier;
    }

    public BigDecimal getBetMultiplier() {
        return betMultiplier;
    }

    public BigDecimal getWinMultiplier() {
        return winMultiplier;
    }

    public BigDecimal getGameMultiplier() {
        return gameMultiplier;
    }

    public Integer getFilter() {
        return filter;
    }

    public Integer getQuiet() {
        return quiet;
    }

    public Integer getLevel() {
        return level;
    }

    public BigDecimal getLastInValue() {
        return lastInValue;
    }

    public BigDecimal getLastOutValue() {
        return lastOutValue;
    }

    public Integer getSendSms() {
        return sendSms;
    }

    public Integer getIsButton() {
        return isButton;
    }

    public BigDecimal getPayoffTemp() {
        return payoffTemp;
    }

    public Integer getIsServer2() {
        return isServer2;
    }

    public Integer getRouter() {
        return router;
    }

    public String getIp() {
        return ip;
    }

    public Integer getTemporaryBlocked() {
        return temporaryBlocked;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setIdStation(Integer idStation) {
        this.idStation = idStation;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public void setIdCashDesk(Integer idCashDesk) {
        this.idCashDesk = idCashDesk;
    }

    public void setIdStationGroup(Integer idStationGroup) {
        this.idStationGroup = idStationGroup;
    }

    public void setIdWatcher(Integer idWatcher) {
        this.idWatcher = idWatcher;
    }

    public void setExternIdentifier(String externIdentifier) {
        this.externIdentifier = externIdentifier;
    }

    public void setExternIdentifierDesk(Integer externIdentifierDesk) {
        this.externIdentifierDesk = externIdentifierDesk;
    }

    public void setExternIdentifierPi(String externIdentifierPi) {
        this.externIdentifierPi = externIdentifierPi;
    }

    public void setFwDesk(String fwDesk) {
        this.fwDesk = fwDesk;
    }

    public void setMultiplier(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public void setSubmitIn(Integer submitIn) {
        this.submitIn = submitIn;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public void setInMultiplier(BigDecimal inMultiplier) {
        this.inMultiplier = inMultiplier;
    }

    public void setOutMultiplier(BigDecimal outMultiplier) {
        this.outMultiplier = outMultiplier;
    }

    public void setBetMultiplier(BigDecimal betMultiplier) {
        this.betMultiplier = betMultiplier;
    }

    public void setWinMultiplier(BigDecimal winMultiplier) {
        this.winMultiplier = winMultiplier;
    }

    public void setGameMultiplier(BigDecimal gameMultiplier) {
        this.gameMultiplier = gameMultiplier;
    }

    public void setFilter(Integer filter) {
        this.filter = filter;
    }

    public void setQuiet(Integer quiet) {
        this.quiet = quiet;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setLastInValue(BigDecimal lastInValue) {
        this.lastInValue = lastInValue;
    }

    public void setLastOutValue(BigDecimal lastOutValue) {
        this.lastOutValue = lastOutValue;
    }

    public void setSendSms(Integer sendSms) {
        this.sendSms = sendSms;
    }

    public void setIsButton(Integer isButton) {
        this.isButton = isButton;
    }

    public void setPayoffTemp(BigDecimal payoffTemp) {
        this.payoffTemp = payoffTemp;
    }

    public void setIsServer2(Integer isServer2) {
        this.isServer2 = isServer2;
    }

    public void setRouter(Integer router) {
        this.router = router;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setTemporaryBlocked(Integer temporaryBlocked) {
        this.temporaryBlocked = temporaryBlocked;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAvailableBalance() {
        if (availableBalance == null)
            return null;
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }


}
