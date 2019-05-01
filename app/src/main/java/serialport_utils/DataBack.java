package serialport_utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by 10188 on 2019/3/21.
 */

public interface DataBack {
    void OnDataReceived(final byte[] buffer, final int size) throws UnsupportedEncodingException;
}
