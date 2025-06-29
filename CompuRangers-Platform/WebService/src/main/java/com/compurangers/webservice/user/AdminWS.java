package com.compurangers.webservice.user;

import com.compurangers.platform.core.domain.sales.dto.GananciasPorMesDTO;
import com.compurangers.platform.core.domain.sales.dto.PedidoPorDiaDTO;
import com.compurangers.platform.core.domain.user.Admin;
import com.compurangers.platform.dao.mysql.sales.OrdenDeVentaDAOImpl;
import com.compurangers.platform.dao.mysql.sales.dto.GananciaPorMesDAOImpl;
import com.compurangers.platform.dao.mysql.sales.dto.PedidoPorDiaDAOImpl;
import com.compurangers.platform.dao.mysql.user.AdminDAOImpl;
import com.compurangers.platform.dao.mysql.user.UsuarioDAOImpl;
import com.compurangers.platform.dao.mysql.user.auth.TokenRecuperacionDAOImpl;
import com.compurangers.platform.service.sales.OrdenDeVentaBO;
import com.compurangers.platform.service.sales.dto.GananciaPorMesBO;
import com.compurangers.platform.service.sales.dto.PedidoPorDiaBO;
import com.compurangers.platform.service.user.AdminBO;
import com.compurangers.platform.service.user.UsuarioBO;
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
    private final UsuarioBO user_bo; 
    
    public AdminWS(){
        this.abo=new AdminBO(new AdminDAOImpl(), new TokenRecuperacionBO(new TokenRecuperacionDAOImpl()));
        this.ov=new OrdenDeVentaBO(new OrdenDeVentaDAOImpl());
        this.ped=new PedidoPorDiaBO(new PedidoPorDiaDAOImpl());
        this.ga=new GananciaPorMesBO(new GananciaPorMesDAOImpl());
        this.user_bo = new UsuarioBO(new UsuarioDAOImpl(), new TokenRecuperacionBO(new TokenRecuperacionDAOImpl()));
     
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
    
    @WebMethod(operationName = "searchAdmin")
    public Admin searchAdmin(@WebParam(name = "id") int id) {
        return abo.searchAdmin(id);
    }
    @WebMethod(operationName = "updateAdmin")
    public boolean updateAdmin(@WebParam(name = "admin") Admin admin) {
        if(!user_bo.updateUsuario((Admin)admin)){
            return false;
        }
        return abo.updateAdmin(admin);
    }
    @WebMethod(operationName = "getGananciasMensuales")
    public List<GananciasPorMesDTO> getGananciasMensuales() {
        return ga.getGananciasMensuales();
    }
    
}
