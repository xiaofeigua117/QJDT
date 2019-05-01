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

public class OrderUtil02 {

    public static Context contextS;
    public static String fancuoma="";
    public static  String base_data="0000";
    public static  String base_order_out="030607E1000102";
    //餐厅灯

    public static final  String light_order="FFF0";
    public static final String open_light1= "0001";
    public static final String open_light2="0002";
    public static final String open_light3="0004";
    public static final String open_light4="0003";
    public static final String open_light5="0005";
    public static final String open_light6="0006";
    public static final String open_light7="0007";
    public static final String open_light_tong="0008";
    public static final String close_light="0000";

    //客厅阳台灯

    public static final  String yangtai_order="FFEF";
    public static final String open_yangtaideng= "0010";
    public static final String close_yangtaideng= "0010";

    //客厅

    public static final  String chaung_order="F9FF";
    public static final String open_chaung= "0200";
    public static final String close_chuang= "0600";

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
                 //打开阳台灯
                dataorder=CRC16M.hexString2binaryString(open_yangtaideng);
                dataBitHandl=yangtai_order;
                break;
            case 10:
                 //关闭阳台灯
                dataorder=CRC16M.hexString2binaryString(close_yangtaideng);
                dataBitHandl=yangtai_order;
                break;
            case 11:
                //开窗户
                dataorder=CRC16M.hexString2binaryString(open_chaung);
                dataBitHandl=chaung_order;
                break;
            case 12:
                //关窗户
                dataorder=CRC16M.hexString2binaryString(close_chuang);
                dataBitHandl=chaung_order;
                break;




        }
        //获取单位操作后的指令
        String order=getorder(dataBitHandl,dataorder);
        Log.i("data-getorder",dataBitHandl+"---"+dataorder);

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

