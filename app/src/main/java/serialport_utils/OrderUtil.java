package serialport_utils;

import android.content.Context;
import android.util.Log;

/**
 * PLC指令发送类
 * Created by blithe on 2018/3/7.
 * 指令根据plc输出位来就计算
 *
 * 控制器：
 * 1.2位控制窗户开启  打开操作：1位置1，2位置1
 *                    关闭操作：1位置0，2位置1
 *                    停止操作：1位置0，2位置1
 * 3.4位控制窗帘开启  （打开操作：3位置1，4位置0
 *                    开关状态：3位置0，4位置0）
 *
 *                    采用触发控制操作：
 *                    正转只改变第三位状态： 置1再置0，开启
 *                                           置1再置0，停止
 *                                           设置标志：flag=1为开启
 *
 *                    反转只改变第三位状态： 置1再置0，开启
 *                                           置1再置0，停止
 *                                           设置标志：flag=1为开启
 * 5.6位控制风机开启  打开操作：5位
 *
 *
 */

public class OrderUtil {

      public static Context contextS;
      public static  String base_data="0000";
      public static  String base_order_out="020607E1000102";
    //餐厅灯

    public static final  String light_order="FFF0";
    public static final String open_light1= "0001";
    public static final String open_light2="0002";
    public static final String open_light3="0004";
    public static final String open_light4="0003";
    public static final String open_light5="0005";
    public static final String open_light6="0006";
    public static final String open_light7="0007";
    public static final String close_light="0000";
    public static final String open_light_tong="0008";
    public static final String tong_order="FFF7";

    public static final String open_light_menting="0010";
    public static final String menting_order="FFEF";
    public static final String close_light_menting="0000";

    //单开厨房灯控制

    public static final String open_kitchen_light="0020";

    public static final String close_kitchen_light="0000";
    public static final String kitchen_order="FFDF";
    //厕所灯控制

    public static final String open_bathroom_light="0040";

    public static final String close_bathroom_light="0000";
    public static final String bathroom_order="FFBF";

    //镜前灯控制

    public static final String open_mirror_light="0080";

    public static final String close_mirror_light="0000";

    public static final String mirror_order="FF7F";



    //厨房窗户控制
    //03 06 07 E1 00 01 02 06 00 5C C7
      public static final String open_window= "0600";

      public static final  String window_order="F9FF";
    //03 06 07 E1 00 01 02 02 00 5E 07
      public static final String close_window="0200";

      public static final String stop_window="0000";



    //厕所开关 打开 开风机输出控制位
    public static final String open_out= "0100";

    public static final  String out_order="FEFF";

    public static final String close_out ="0000";


    /***
     * 板子第一位控制
     * 第二位电源
     * 0003  为全部开启
     */
    public static void senOrder2(int type){
        String dataorder="";
        String dataBitHandl ="";
        Context context=contextS;
        switch (type){
            //开zhudeng1
            case 0:
                dataorder=CRC16M.hexString2binaryString(close_light);
                dataBitHandl=light_order;
                break;
            case 1:
                dataorder=CRC16M.hexString2binaryString(open_light1);
                dataBitHandl=light_order;
                break;
            //主灯2
            case 2:
                dataorder=CRC16M.hexString2binaryString(open_light2);
                dataBitHandl=light_order;
                break;
            //主灯3：
            case 3:
                dataorder=CRC16M.hexString2binaryString(open_light3);
                dataBitHandl=light_order;
                break;
            case 4:
                //
                dataorder=CRC16M.hexString2binaryString(open_light4);
                dataBitHandl=light_order;
                break;
            case 5:
                dataorder=CRC16M.hexString2binaryString(open_light5);
                dataBitHandl=light_order;
                break;
            case 6:
                dataorder=CRC16M.hexString2binaryString(open_light6);
                dataBitHandl=light_order;
                break;
            case 7:
                dataorder=CRC16M.hexString2binaryString(open_light7);
                dataBitHandl=light_order;
                break;
            case 8:
                dataorder=CRC16M.hexString2binaryString(open_light_tong);
                dataBitHandl=light_order;
                break;
            case 9:
                //开门厅筒灯
                dataorder=CRC16M.hexString2binaryString(open_light_menting);
                dataBitHandl=menting_order;
               break;
            case 10:
                //关门厅筒灯
                dataorder=CRC16M.hexString2binaryString(open_light_menting);
                dataBitHandl=menting_order;
               break;
            case 11:
                //打开厨房灯
                dataorder=CRC16M.hexString2binaryString(open_kitchen_light);
                dataBitHandl=kitchen_order;
               break;
            case 12:
                //关闭厨房灯
                dataorder=CRC16M.hexString2binaryString(close_kitchen_light);
                dataBitHandl=kitchen_order;
                break;
                //返回值类型
                //    01 03 0E 02 2B 00 17 00 09
                //TX:01 03 00 00 00 07 04 08

                // RX:01 03 0E 02 80 00 24 00 0E 01 18 3A 62 63 6E 01 5E BB 28
                //表示从起始地址00 00 读取CO2，TVOC，CH2O，PM2.5，H，T, PM10数据
                //0024  000e 0118 3a62 636e 015e
            case 13:
                //打开厕所灯
                dataorder=CRC16M.hexString2binaryString(open_bathroom_light);
                dataBitHandl=bathroom_order;
                break;
            case 14:
                //关闭厕所灯
              dataorder=CRC16M.hexString2binaryString(close_bathroom_light);
                dataBitHandl=bathroom_order;
                break;
            case 15:
                //开镜子灯
                dataorder=CRC16M.hexString2binaryString(open_mirror_light);
                dataBitHandl=mirror_order;
                break;
            case 16:
                //关镜子灯
                dataorder=CRC16M.hexString2binaryString(close_mirror_light);
                dataBitHandl=mirror_order;
                break;
            case 17:
                //风机联动开
                dataorder=CRC16M.hexString2binaryString(open_out);
                dataBitHandl=out_order;
                Log.i("1—16","风机控制打开");
                break;
            case 18:
                //风机联动关
                dataorder=CRC16M.hexString2binaryString(close_out);
                dataBitHandl=out_order;
                Log.i("1—17","风机控制关闭");
                break;
            case 19:
                //厨房窗户开
                dataorder=CRC16M.hexString2binaryString(open_window);
                dataBitHandl=window_order;
                break;
            case 20:
                //厨房窗户关
                dataorder=CRC16M.hexString2binaryString(close_window);
                dataBitHandl=window_order;
                break;
            case 21:
                //单开筒灯
                dataorder=CRC16M.hexString2binaryString(open_light_tong);
                dataBitHandl=tong_order;
                break;
            case 22:
                //单关筒灯
                dataorder=CRC16M.hexString2binaryString(close_window);
                dataBitHandl=tong_order;
                break;


        }
        //获取单位操作后的指令
        String order=getorder(dataBitHandl,dataorder);
        //发送数据
        PLCSerialPortUtil.sendData(type,order);
       // Myhelper.alertDialog(context,order);
    }
    static int type1;
    //开窗帘操作 复杂封装


    /**
     *  modbus 计算函数  根究数据位与base指令计算
     *   完整校验以及指令码
     * @param dataBitHandl
     * @param dataorder
     * @return String 功能modbus指令
     */
     public  static  String getorder(String dataBitHandl,String dataorder){
        //crc校验指令
         base_data =CRC16M.yuBybit(CRC16M.hexString2binaryString(base_data),CRC16M.hexString2binaryString(dataBitHandl));
         //按位操作
         base_data=CRC16M.HuoBybit(base_data,dataorder);

         base_data=CRC16M.binaryString2hexString(base_data);

         String order=base_order_out.concat(base_data);

         byte[] modbusOrder=CRC16M.getSendBuf(order);

         order=CRC16M.getBufHexStr(modbusOrder);

         return order;
     }

}

