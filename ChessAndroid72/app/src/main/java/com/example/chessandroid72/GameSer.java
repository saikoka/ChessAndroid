package com.example.chessandroid72;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import static com.example.chessandroid72.BoardAdapter.board;
import java.util.Calendar;

import model.Piece;


public class GameSer implements Serializable{

    public String name;
    public String date;
    private static final long serialVersionUID = -5406792576444101056L;
    public static List<GameSer> gameSerList = new ArrayList<>();


    public GameSer(String name){
        this.name=name;
        gameSerList.add(this);
    }

    public List<Piece[][]> states = new ArrayList<>();


    public static GameSer findGame(String x){
        for (GameSer gameSer: gameSerList){
            if (gameSer.name.equals(x)){
                return gameSer;
            }
        }
        return null;
    }

    public static GameSer findGame2(String x){
        for (GameSer gameSer: gameSerList){
            if (gameSer.date.equals(x)){
                return gameSer;
            }
        }
        return null;
    }


    @Override
    public String toString(){
        return name;
    }


}
