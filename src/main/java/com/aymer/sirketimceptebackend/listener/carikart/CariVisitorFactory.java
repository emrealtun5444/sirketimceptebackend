package com.aymer.sirketimceptebackend.listener.carikart;

import com.aymer.sirketimceptebackend.listener.carikart.visitor.CariBorcAlacakVisitor;
import com.aymer.sirketimceptebackend.listener.carikart.visitor.CariFaturaVisitor;
import com.aymer.sirketimceptebackend.listener.carikart.visitor.CariKartVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class CariVisitorFactory {

    @Autowired
    private ApplicationContext context;

    public List<CariKartVisitor> getCariVisitorList() {
        List<CariKartVisitor> cariKartVisitorList = new LinkedList<>();
        cariKartVisitorList.add(context.getBean("cariBorcAlacakVisitor", CariBorcAlacakVisitor.class));
        cariKartVisitorList.add(context.getBean("cariFaturaVisitor", CariFaturaVisitor.class));
        return cariKartVisitorList;
    }
}
