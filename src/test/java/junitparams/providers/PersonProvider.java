package junitparams.providers;

import junitparams.Person;
import junitparams.utils.ExcelUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by goonawardanan on 21/03/2016.
 */
public class PersonProvider {
    private static String fileNameWithPath = "C:\\Users\\goonawardanan\\projects\\SpringRestApp\\src\\test\\resources\\users.xlsx";
//    public static Object[] provideAdults() {
//        return new Object[]{
//                new Object[]{new Person(25), true},
//                new Object[]{new Person(32), true}
//        };
//    }
//
//    public static Object[] provideTeens() {
//        return new Object[]{
//                new Object[]{new Person(12), false},
//                new Object[]{new Person(1768), false},
//                new Object[]{new Person(1), true},
//                new Object[]{new Person(17), false}
//        };
//    }


    public static Object[] provideTeens() {
        ArrayList<String> strArr = null;
        ArrayList<String> strStatus = null;
        try {
            strArr = ExcelUtil.getCellsContentFromAGivenSheetNumberOfAGivenXLSFile(fileNameWithPath,0,"age");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            strStatus = ExcelUtil.getCellsStringContentFromAGivenSheetNumberOfAGivenXLSFile(fileNameWithPath,0,"status");
        } catch (IOException e) {
            e.printStackTrace();
        }


        int statusIndex = 0;
        ArrayList<Object> obbarrlist = new ArrayList<Object>();
        for(String content:strArr){
            int age = Integer.valueOf(content);
            obbarrlist.add(new Object[]{new Person(age),strStatus.get(statusIndex)});
            statusIndex++;
        }
        Object[] obarr= new Object[obbarrlist.size()];
        for (int x =0 ; x<obbarrlist.size();x++){
            obarr[x] = obbarrlist.get(x);

        }


        return obarr;
    }
}
