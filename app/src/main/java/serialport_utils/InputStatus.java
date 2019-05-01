package serialport_utils;

import android.util.Log;

/**
 * Created by 10188 on 2019/1/8.
 * 板子状态
 * 读到数据和之前比较，不一样（处理），保存本次数据
 *
 */

public class InputStatus {
    public static String input12;
    public static String input12_pre="000000000000";
    public  static int key_restroom=0;
    public  static int rengan_bathroom=0;
    public  static int key_lighton_restroom=0;
    public  static int key_lightoff_restroom=0;


    public  static int fan_on=0;
    public  static int chufangkai=0;
    public  static int chufangguan=0;
    public static boolean winOpenFlag=true;
    public  static int Is_openwin=0;
    public  static int Is_closeWin=0;
    public  static int door_open=0;
    public  static int door_close=0;

    public static int classLight=1;
    public static int classLight_pre=0;
    public static boolean KeyLight=false;
    public static boolean KeyLight_pre=false;
    public static int key_flag=0;
    public InputStatus(){
    }


    public static  void getInput12(String fanhuizhi){
//        板子状态检测
        //4为数据按位转化16进制对应12输
        input12=(String)CRC16M.hexString2binaryString(fanhuizhi).substring(4,16);
        //比较灯开关数据
        //为灯光等级赋值
        if(!input12.substring(10,11).equals(input12_pre.substring(10,11))){
            classLight++;
            if(classLight>5){
                classLight=1;
            }
        }
        //为主开关赋值
        if(!input12.substring(11,12).equals(input12_pre.substring(11,12))){
             KeyLight=!KeyLight;
        }
        //窗户感应\
        if(input12_pre.substring(9,10).equals("1")&&"0".equals(input12.substring(9,10))){
            chufangkai=1;
        }if(input12_pre.substring(9,10).equals("0")&&"1".equals(input12.substring(9,10))){
            chufangguan=1;
        }

        if(input12_pre.substring(8,9).equals("0")&&!input12_pre.substring(8,9).equals(input12.substring(8,9))){

            if(winOpenFlag){
               Is_openwin=1;
            }else {
                Is_closeWin=1;
            }
            winOpenFlag=!winOpenFlag;

        }
        //这是风机开关
        if(!input12_pre.substring(7,8).equals(input12.substring(7,8))){
            key_restroom=1;
        }
        if(input12_pre.substring(6,7).equals("1")&&"0".equals(input12.substring(6,7))){
            key_lighton_restroom=1;
        }if(input12_pre.substring(6,7).equals("0")&&"1".equals(input12.substring(6,7))){
            key_lightoff_restroom=1;
        }


        if(input12_pre.substring(5,6).equals("1")&&"0".equals(input12.substring(5,6))){
            door_open=1;
        }if(input12_pre.substring(5,6).equals("0")&&"1".equals(input12.substring(5,6))){
            door_close=1;
        }


        //电动窗开关
         //厕所人体检测
        if(input12_pre.substring(2,3).equals("0")&&"1".equals(input12.substring(2,3))){
            rengan_bathroom=1;
        }
        //

        //厕所开关  比较复杂  需要考虑到联动
        //keyLight 为1才是开灯
        //保存新数据
        input12_pre=input12;

    }
}
