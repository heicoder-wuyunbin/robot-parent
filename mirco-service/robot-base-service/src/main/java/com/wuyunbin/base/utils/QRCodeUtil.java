package com.wuyunbin.base.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtil {

    /**
     * 生成二维码并保存到文件
     *
     * @param text     要编码的内容
     * @param width    二维码宽度
     * @param height   二维码高度
     * @param filePath 保存二维码的文件路径
     * @throws WriterException 编码错误
     * @throws IOException     IO错误
     */
    public static void generateQRCode(String text, int size, String filePath) throws WriterException, IOException {
        // 配置编码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置编码格式
        hints.put(EncodeHintType.MARGIN, 1); // 设置边距

        // 创建二维码矩阵
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size, hints);

        // 保存二维码为文件
        Path path = new File(filePath).toPath();
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void main(String[] args) {
        try {
            // 示例：生成一个二维码
            String text = "/pages/pay/index/index";
            String filePath = "qrcode.png";
            int size = 300; // 二维码宽高


            generateQRCode(text, size, filePath);
            System.out.println("二维码已成功生成并保存到：" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}