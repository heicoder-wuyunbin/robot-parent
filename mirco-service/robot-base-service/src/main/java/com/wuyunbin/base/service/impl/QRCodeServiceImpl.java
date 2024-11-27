package com.wuyunbin.base.service.impl;

import com.google.zxing.WriterException;
import com.wuyunbin.base.QRCode;
import com.wuyunbin.base.service.QRCodeService;
import com.wuyunbin.base.utils.QRCodeUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QRCodeServiceImpl implements QRCodeService {
    @Resource
    private QRCodeUtil qrCodeUtil;

    @Override
    public String generateQRCode(QRCode qrCode) {
        String result="";
        try {
            result=qrCodeUtil.generateQRCode(qrCode.getText(), qrCode.getSize(), qrCode.getFilePath());
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
