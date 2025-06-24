package com.compurangers.webservice.user;

import com.compurangers.platform.core.domain.sales.dto.GananciasPorMesDTO;
import com.compurangers.platform.core.domain.sales.dto.PedidoPorDiaDTO;
import com.compurangers.platform.dao.mysql.sales.OrdenDeVentaDAOImpl;
import com.compurangers.platform.dao.mysql.sales.dto.GananciaPorMesDAOImpl;
import com.compurangers.platform.dao.mysql.sales.dto.PedidoPorDiaDAOImpl;
import com.compurangers.platform.dao.mysql.user.AdminDAOImpl;
import com.compurangers.platform.dao.mysql.user.auth.TokenRecuperacionDAOImpl;
import com.compurangers.platform.service.sales.OrdenDeVentaBO;
import com.compurangers.platform.service.sales.dto.GananciaPorMesBO;
import com.compurangers.platform.service.sales.dto.PedidoPorDiaBO;
import com.compurangers.platform.service.user.AdminBO;
import com.compurangers.platform.service.user.auth.TokenRecuperacionBO;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

@WebService(serviceName = "AdminWS", targetNamespace = "http://services.compurangers.com")
public class AdminWS {
    
    private final AdminBO abo;
    private final OrdenDeVentaBO ov;
    private final PedidoPorDiaBO ped;
    private final GananciaPorMesBO ga;
    
    public AdminWS(){
        this.abo=new AdminBO(new AdminDAOImpl(), new TokenRecuperacionBO(new TokenRecuperacionDAOImpl()));
        this.ov=new OrdenDeVentaBO(new OrdenDeVentaDAOImpl());
        this.ped=new PedidoPorDiaBO(new PedidoPorDiaDAOImpl());
        this.ga=new GananciaPorMesBO(new GananciaPorMesDAOImpl());
    }
    
    @WebMethod(operationName = "getGananciaMes")
    public double getGananciaMes() {
        return abo.getGananciaMes();
    }
    
    @WebMethod(operationName = "getTotalHistorico")
    public double getTotalHistorico() {
        return ov.getTotalHistorico();
    }
    
    @WebMethod(operationName = "getPedidosHoy")
    public int getPedidosHoy() {
        return ov.getPedidosHoy();
    }
    
    @WebMethod(operationName = "getPedidosSemanal")
    public List<PedidoPorDiaDTO> getPedidosSemanal() {
        return ped.getPedidosSemana();
    }
    
    @WebMethod(operationName = "getGananciasMensuales")
    public List<GananciasPorMesDTO> getGananciasMensuales() {
        return ga.getGananciasMensuales();
    }
    
}
