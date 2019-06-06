package edu.mmc.util;

import edu.mmc.entity.vo.OptionEntity;
import edu.mmc.entity.vo.SeriesEntity;
import edu.mmc.entity.vo.TotalVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartHandle {
    public static OptionEntity opt(List<List<TotalVo>> dli, String title,String legend[]){
        List<Object> series = new ArrayList();
        String [] xAxis = new String[dli.get(0).size()];
        for(int i=0;i<dli.size();i++){
            if(i == 0){
                for (int j = 0; j < dli.get(0).size(); j++)
                    xAxis[j] = dli.get(0).get(j).getFaculty();
            }
            List<Integer> data = dli.get(i).stream().map(e -> e.getNum()).collect(Collectors.toList());
            SeriesEntity en = new SeriesEntity();
            en.setName(legend[i]);
            en.setType("bar");
            en.setData(data);
            series.add(en);
        }
        OptionEntity op = new OptionEntity(xAxis,series,legend,title);
        return op;
    }
}
