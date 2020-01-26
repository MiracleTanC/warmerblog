package warmer.star.blog.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomNameUtil {

    public static String getRandomName(){
        String name = null;
        try {
            name = firstName() + secondNameNoSex();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("生成随机姓名："+name);
        return name;
    }
    /*
     * 随机返回a和b其中一个数
     */
    public static int randomAB(int a, int b){
        return (int)((Math.random()*Math.abs(a-b))+ Math.min(a, b));
    }

    /**
     * 生成姓氏
     * @throws IOException
     */
    private static String firstName() throws IOException{
        List<String> fistNames = loadBaiJiaXing("static/asserts/百家姓");
        return fistNames.get(randomAB(0,fistNames.size()));
    }

    /**
     * 读取姓氏文件，获取姓氏
     * @return
     * @throws IOException
     */
    private static List<String> loadBaiJiaXing(String path) throws IOException{
        //使用类加载器来加载文件
        Resource res = new ClassPathResource(path);
        InputStream in = res.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
        //文件读取
        String line = null;
        //结果集合
        List<String> result = new ArrayList<>(200);
        while((line=br.readLine())!=null){
            line = line.trim();
            //使用空白字符分割
            String[] names = line.split("\\s+");
            result.addAll(Arrays.asList(names));
        }
        return result;
    }

    /**
     * @生成名字
     * @return
     * @throws IOException
     */
    private static String secondName(boolean male) throws IOException{
        if(male){
            List<String> names = loadNames("static/asserts/男性");
            return names.get(randomAB(0,names.size()));
        }else{
            List<String> names = loadNames("static/asserts/女性");
            return names.get(randomAB(0,names.size()));
        }
    }
    /**
     * @生成名字,不分男女
     * @return
     * @throws IOException
     */
    private static String secondNameNoSex() throws IOException{
        List<String> names = loadNames("static/asserts/男性");
        names.addAll(loadNames("static/asserts/女性"));
        return names.get(randomAB(0,names.size()));
    }
    /**
     * 读取百家姓文件，获取名字
     * @return
     * @throws IOException
     */
    private static List<String> loadNames(String path) throws IOException {
        Resource res = new ClassPathResource(path);
        InputStream in = res.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
        //文件读取
        String line = null;
        //结果集合
        List<String> result = new ArrayList<>(200);
        while((line=br.readLine())!=null){
            line = line.trim();
            //使用空白字符分割
            String[] names = line.split("\\s+");
            result.addAll(Arrays.asList(names));
        }
        return result;
    }

}
