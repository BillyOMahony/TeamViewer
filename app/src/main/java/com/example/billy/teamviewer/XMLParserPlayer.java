package com.example.billy.teamviewer;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Billy on 08/11/2017.
 */
public class XMLParserPlayer {
    private Player [] data = null;
    Context context = null;

    public XMLParserPlayer(Context c){
        this.context = c;

        // grab the data from xml


        // get the doc parser ready for test.xml
        InputStream is = this.context.getResources().openRawResource(R.raw.player);
        DocumentBuilder builder = null;
        Document doc = null;

        try{
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(is);
        }
        catch(ParserConfigurationException e){
            e.printStackTrace();
        }
        catch(SAXException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        // chop all the nodes
        NodeList fnameList = doc.getElementsByTagName("firstname");
        NodeList lnameList = doc.getElementsByTagName("lastname");
        NodeList nationalityList = doc.getElementsByTagName("nationality");
        NodeList numList = doc.getElementsByTagName("number");
        NodeList positionList = doc.getElementsByTagName("position");
        NodeList sideList = doc.getElementsByTagName("side");
        NodeList dobList = doc.getElementsByTagName("dateofbirth");
        NodeList cjdList = doc.getElementsByTagName("clubjoindate");
        NodeList imageList = doc.getElementsByTagName("image");
        NodeList urlList = doc.getElementsByTagName("url");


        // traverse NodeLists to make the data array
        data = new Player[fnameList.getLength()];

        for(int i = 0; i < fnameList.getLength(); i++){
            String fname = fnameList.item(i).getFirstChild().getNodeValue();
            String lname = lnameList.item(i).getFirstChild().getNodeValue();
            String nationality = nationalityList.item(i).getFirstChild().getNodeValue();
            String number = numList.item(i).getFirstChild().getNodeValue();
            String position = positionList.item(i).getFirstChild().getNodeValue();
            String side = sideList.item(i).getFirstChild().getNodeValue();
            String dateOfBirth = dobList.item(i).getFirstChild().getNodeValue();
            String clubJoinDate = cjdList.item(i).getFirstChild().getNodeValue();
            String image = imageList.item(i).getFirstChild().getNodeValue();
            String url = urlList.item(i).getFirstChild().getNodeValue();


            data[i] = new Player(fname, lname, nationality, number, position, side, dateOfBirth, clubJoinDate, image, url);
        }
    }

    // returns a copy of the array. Necessary for the pitch view creation
    public Player[] getData(){
        return Arrays.copyOf(data, data.length);
    }

    public Player getData(int i){
        return data[i];
    }

    public Player getData(String lastName){

        for(int i = 0; i < data.length; i++){
            if(data[i] != null && data[i].getLastName().equals(lastName)){
                return data[i];
            }else if(data[i] != null){
                Log.w("WARNING: ", data[i].getLastName() + " != " + lastName);
            }else{
                Log.w("WARNING: ", "Data[" + i + "] is NULL" );
            }
        }

        return null;
    }


    public String[] getLastNames(){
        String[] lnames = new String[data.length];
        for(int i = 0; i < lnames.length; i++){
            lnames[i] = getData(i).getLastName();
        }
        return lnames;
    }
}
