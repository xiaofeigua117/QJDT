package serialport_utils;

import java.text.DecimalFormat;

/**
 *  传感器操作类
 *  1.读取6合一传感器数�?
 * Created by BlitheLee on 2018/5/14.
 */

public class SenserUtils {
    private static int SENSER_TYPE=12;

    /**
     * @return String[] 字符串数�? {}
     *
     */
    public static void readAllData(){
       // String [] datas=null;
        //发送
       PLCSerialPortUtil.sendData(SENSER_TYPE,"0103000000070408");
        //if(datas==null){
        //    Log.i("senserutil----","读取数据为null");
        //}else{
       // }
    }

    /**
     * @param  data 传感器字符串
     * @return String[] 返回String[]类型数据
     *   data={CO2,TVOC,CH20,PM2.5,Humidity,Temperature,PM10}
     * */
    public static String[] getSenserGroup(String data){
        //传感器数据格式：RX:01030E  02800024000E01183A62636E015E
        //                   02030E  013F003F005000553F3F563F00713F
        String [] datas=new String[7];
        data=data.replace(" ","");
        data=data.substring(6,34);
        for(int i=1;i<=7;i++){
            switch (i){
                case 1:
//                    ＝CO2_H*256+CO2_L
                    int co2_double=Integer.parseInt(data.substring(0,2),16)*256+Integer.parseInt(data.substring(2,4),16);
                    datas[0]=co2_double+"";
                    DataSenser.dataSensers[0]="二氧化碳含量为："+datas[0]+"ppm";
                    break;
                case 2:
//                	＝(TVOC_H*256+TVOC_L)/10.0
//                	挥发行有机物  正常浓度小于500ug/m3
                    int tvoc=Integer.parseInt(data.substring(4,6),16)*256+Integer.parseInt(data.substring(6,8),16);
                    double zhi=(double)tvoc/10;
                    datas[1]=zhi+"";
                    DataSenser.dataSensers[1]="有机物含量："+datas[1]+"ug/m3";
                    break;
                case 3:
                    //甲醛
                    int ch2o=Integer.parseInt(data.substring(8,10),16)*256+Integer.parseInt(data.substring(10,12),16);
                    datas[2]=(double)ch2o/10+"";
                    DataSenser.dataSensers[2]="甲醛含量："+datas[2]+"ppm";
                    break;
                case 4:
//                	pm2.5
                    int Pm2=Integer.parseInt(data.substring(12,14),16)*256+Integer.parseInt(data.substring(14,16),16);
                    datas[3]=Pm2+"";
                    DataSenser.dataSensers[3]="Pm2.5含量："+datas[3]+"ug/m3";
                    break;
                case 5:
                    //湿度
                    int Humidity=Integer.parseInt(data.substring(16,18),16)*256+Integer.parseInt(data.substring(18,20),16);
                    int preHhumidity=125*Humidity/(65536)-6;
                    datas[4]=preHhumidity+"";
                    DataSenser.dataSensers[4]="房屋湿度："+datas[4]+"%";
                    break;
                case 6:
                    //温度
                	int temperature=Integer.parseInt(data.substring(20,22),16)*256+Integer.parseInt(data.substring(22,24),16);
                    double tt=175.72*(double)temperature/(65536)-46.85;
                    DecimalFormat    df   = new DecimalFormat("######0.00");
                    datas[5]=df.format(tt)+"";
                    DataSenser.dataSensers[5]="房屋温度："+datas[5]+"℃";
                    break;
                case 7:
                    //pm10
                    int pm10=Integer.parseInt(data.substring(24,26),16)*256+Integer.parseInt(data.substring(26,28),16);
                    datas[6]=pm10+"";
                    DataSenser.dataSensers[6]="PM10含量："+datas[6]+"ug/m3";
                    break;
            }
        }

        return datas;
    }

}
