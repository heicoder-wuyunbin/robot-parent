package com.wuyunbin.base.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class QRCodeUtil {
    public String generateQRCode(String text, int size, String filePath) throws WriterException, IOException {
        // 配置编码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置编码格式
        hints.put(EncodeHintType.MARGIN, 1); // 设置边距

        // 创建二维码矩阵
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size, hints);

        // 将二维码矩阵写入字节数组输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        // 将字节流转换为Base64字符串
        byte[] qrCodeBytes = outputStream.toByteArray();
        String base64QRCode = Base64.getEncoder().encodeToString(qrCodeBytes);

        // 关闭流
        outputStream.close();

        return "data:image/png;base64," + base64QRCode; // 返回Base64格式的二维码图片
    }
}