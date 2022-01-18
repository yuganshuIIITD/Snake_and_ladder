package com.example.ap_project;

public class Snake {
    int init_pos;
    int fin_pos;
    int finX;
    int finY;
    String dir;
    Snake(int init_pos, int fin_pos , int finX, int finY,String dir){
        this.dir = dir;
        this.init_pos = init_pos;
        this.fin_pos = fin_pos;
        this.finX = finX;
        this.finY = finY;
    }
}
