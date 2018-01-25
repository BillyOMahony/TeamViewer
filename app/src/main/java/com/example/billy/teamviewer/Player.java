package com.example.billy.teamviewer;

import java.io.Serializable;

/**
 * Created by Billy on 08/11/2017.
 */
public class Player implements Serializable {
    private String _firstName;
    private String _lastName;
    private String _nationality;
    private String _number;
    private String _position;
    private String _side;
    private String _dateOfBirth;
    private String _clubJoinDate;
    private String _img;
    private String _url;


    public Player(String firstName, String lastName, String nationality, String num, String position, String side, String dateOfBirth, String clubJoinDate, String img, String url) {
        this._firstName = firstName;
        this._lastName = lastName;
        this._nationality = nationality;
        this._number = num;
        this._position = position;
        this._side = side;
        this._dateOfBirth = dateOfBirth;
        this._clubJoinDate = clubJoinDate;
        this._img = img;
        this._url = url;

    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {return _lastName;}

    public String getNationality() {return _nationality;}

    public String getNumber() {return _number;}

    public String getPosition() { return _position;}

    public String getSide() {return _side;}

    public String getDOB() {return _dateOfBirth;}

    public String getCJD(){return _clubJoinDate;}

    public String getImage() {
        return _img;
    }

    public String getUrl() {return _url;}

}
