package model;

import java.util.Calendar;

public class PremiumUser extends User {

    // attributes
    private String nickname;
    private String avatar;
    private String card;
    private int lastPaidMonth;
    private double[] payments;

    // methods
    public PremiumUser(String name, String email, String password, String id, int internalID, Calendar initDate, String nickname, String avatar,
            String card, int lastPaidMonth) {
        super(name, email, password, id, internalID, initDate, false);
        this.nickname = nickname;
        this.avatar = avatar;
        this.card = card;
        this.lastPaidMonth = lastPaidMonth;
        this.payments = new double[12]; // 12 months in a year
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getLastPaidMonth() {
        return lastPaidMonth;
    }

    public void setLastPaidMonth(int lastPaidMonth) {
        this.lastPaidMonth = lastPaidMonth;
    }

    public double[] getPayments() {
        return payments;
    }

    public void setPayments(double[] payments) {
        this.payments = payments;
    }

    @Override
    public String surprise(int month, char randLetter) {
        var info = "";
        // TODO: implement
        return info;
    }

}