<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master" CodeBehind="Ventas.aspx.cs" Inherits="Web.Admin" %>

<asp:Content ID="HomeContent" ContentPlaceHolderID="MainContent" runat="server">

    <main>
        <h2 class="mb-4">Dashboard de Ventas</h2>
        <!-- Métricas principales -->
        <div class="row g-4 mb-4">
            <div class="col-md-3">
                <div class="card shadow-sm border-0">
                    <div class="card-body text-center">
                        <h6 class="card-title text-muted">Total Ventas</h6>
                        <h2 class="fw-bold text-success">$12,300</h2>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow-sm border-0">
                    <div class="card-body text-center">
                        <h6 class="card-title text-muted">Ganancias Mes</h6>
                        <h2 class="fw-bold text-primary">$2,150</h2>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow-sm border-0">
                    <div class="card-body text-center">
                        <h6 class="card-title text-muted">Pedidos Hoy</h6>
                        <h2 class="fw-bold text-info">38</h2>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow-sm border-0">
                    <div class="card-body text-center">
                        <h6 class="card-title text-muted">Clientes Nuevos</h6>
                        <h2 class="fw-bold text-warning">7</h2>
                    </div>
                </div>
            </div>
        </div>

        <!-- Ganancias por mes -->
        <div class="card mb-4 shadow-sm border-0">
            <div class="card-header bg-white">
                <h5 class="mb-0">Ganancias por mes</h5>
            </div>
            <div class="card-body">
                <div class="row text-center">
                    <div class="col">
                        <div class="fw-bold">Enero</div>
                        <div>$1,200</div>
                    </div>
                    <div class="col">
                        <div class="fw-bold">Febrero</div>
                        <div>$1,350</div>
                    </div>
                    <div class="col">
                        <div class="fw-bold">Marzo</div>
                        <div>$1,550</div>
                    </div>
                    <div class="col">
                        <div class="fw-bold">Abril</div>
                        <div>$1,800</div>
                    </div>
                    <div class="col">
                        <div class="fw-bold">Mayo</div>
                        <div>$2,150</div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Productos más vendidos -->
        <div class="card mb-4 shadow-sm border-0">
            <div class="card-header bg-white">
                <h5 class="mb-0">Productos más vendidos</h5>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        Producto A
                        <span class="badge bg-success rounded-pill">215</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        Producto B
                        <span class="badge bg-success rounded-pill">178</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        Producto C
                        <span class="badge bg-success rounded-pill">130</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        Producto D
                        <span class="badge bg-success rounded-pill">112</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        Producto E
                        <span class="badge bg-success rounded-pill">96</span>
                    </li>
                </ul>
            </div>
        </div>

        <!-- Pedidos por día -->
        <div class="card shadow-sm border-0 mb-4">
            <div class="card-header bg-white">
                <h5 class="mb-0">Pedidos por día (última semana)</h5>
            </div>
            <div class="card-body">
                <div class="row text-center">
                    <div class="col">
                        <div class="fw-bold">Lun</div>
                        <div>31</div>
                    </div>
                    <div class="col">
                        <div class="fw-bold">Mar</div>
                        <div>29</div>
                    </div>
                    <div class="col">
                        <div class="fw-bold">Mié</div>
                        <div>27</div>
                    </div>
                    <div class="col">
                        <div class="fw-bold">Jue</div>
                        <div>33</div>
                    </div>
                    <div class="col">
                        <div class="fw-bold">Vie</div>
                        <div>38</div>
                    </div>
                    <div class="col">
                        <div class="fw-bold">Sáb</div>
                        <div>22</div>
                    </div>
                    <div class="col">
                        <div class="fw-bold">Dom</div>
                        <div>19</div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</asp:Content>
