package com.example.myapplication;


import android.graphics.Bitmap;
import android.graphics.RectF;

import java.util.List;

/**
 * Created by amitshekhar on 06/03/17.
 */

/**
 * 通用接口,用于与不同的识别引擎交互
 */
public interface Classifier {

    // Classifier返回的不可变结果，用于描述被识别的内容
    public class Recognition {

        //已识别内容的唯一标识符。特定于类，而不是对象的实例。
        private final String id;
        //显示识别的名称。
        private final String title;
        //一个可分类的分数，衡量识别的好坏。越高越好。
        private final Float confidence;
        //在源图像中可选的位置，用于识别对象的位置。
        //Rect F holds four float coordinates for a rectangle .
        //我们用不到
        private RectF location;
        //构造函数
        public Recognition(
                final String id, final String title, final Float confidence, final RectF location) {
            this.id = id;
            this.title = title;
            this.confidence = confidence;
            this.location = location;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public Float getConfidence() {
            return confidence;
        }

        public RectF getLocation() {
            return new RectF(location);
        }

        public void setLocation(RectF location) {
            this.location = location;
        }

        @Override
        public String toString() {
            String resultString = "";
            if (id != null) {
                resultString += "[" + id + "] ";
            }

            if (title != null) {
                resultString += title + " ";
            }

            if (confidence != null) {
                resultString += String.format("(%.1f%%) ", confidence * 100.0f);
            }

            if (location != null) {
                resultString += location + " ";
            }

            return resultString.trim();
        }
    }

    List<Recognition> recognizeImage(Bitmap bitmap);

    void enableStatLogging(final boolean debug);

    String getStatString();

    void close();
}
