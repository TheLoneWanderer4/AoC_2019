package AOC;

import AOC.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Image {

    private List<Integer[][]> layers;
    private int width;
    private int height;


    public Image(String data, int width, int height){
        layers = new ArrayList<>();
        this.width = width;
        this.height  = height;
        process(data);
    }

    private void process(String data){
        Integer[][] layer = new Integer[height][width];

        int widthPointer = 0;
        int heightPointer = 0;

        for(int i = 0; i < data.length(); i++){
            Integer digit = Integer.parseInt(String.valueOf(data.charAt(i)));

            layer[heightPointer][widthPointer] = digit;
            widthPointer += 1;

            if(widthPointer >= width){
                widthPointer = 0;
                heightPointer += 1;
            }

            if(heightPointer >= height){
                layers.add(layer);
                layer = new Integer[height][width];
                heightPointer = 0;
            }

        }
    }

    public int verify(){
        Integer[][] test = layerWithLeastZero();
        return getNumNum(test,1) * getNumNum(test,2);
    }

    private Integer[][] layerWithLeastZero(){
        Integer[][] min = null;
        int minZero = Integer.MAX_VALUE;
        for(Integer[][] layer : layers){
            int numZero = getNumNum(layer, 0);
            if(numZero < minZero){
                minZero = numZero;
                min = layer;
            }
        }
        return min;
    }

    private int getNumNum(Integer[][] layer, int num){
        int ret = 0;
        for(Integer[] row : layer){
            for(Integer digit : row){
                if(digit == num){
                    ret += 1;
                }
            }
        }
        return ret;
    }

    private Integer[][] getFinalImage(){
        Integer[][] ret = new Integer[height][width];

        List<Integer[][]> hold = new ArrayList<>(layers);
        Collections.reverse(hold);

        for(Integer[][] layer : hold){
            for(int row = 0; row < layer.length;row++){
                for(int col = 0; col < layer[row].length;col++){
                    if(layer[row][col] != 2){
                        ret[row][col] = layer[row][col];
                    }
                }
            }
        }
        return ret;
    }

    public String finalImage(){
        Integer[][] image = getFinalImage();
        String ret = "{\n";
        for(Integer[] row : image){
            ret += Util.printArray(row) + "\n";
        }
        return ret + "}";
    }

    public String toString(){
        String ret = "{\n";
        for(Integer[][] layer: layers){
            for(Integer[] row : layer){
                ret += Util.printArray(row) + "\n";
            }
            ret += "\n";
        }
        return ret.substring(0,ret.length()-1) + "}";
    }
}
