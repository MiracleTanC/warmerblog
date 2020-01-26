package warmer.star.blog.util;

import lombok.Data;

@Data
public class QiniuFileQueryItem {
    private String marker;
    private int limit;
    private String prefix;
    private String delimiter;
}
