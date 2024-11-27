package com.wuyunbin.base;

import lombok.Data;

@Data
public class QRCode {
    private String text;
    private int size=10;
    private String filePath;
}
