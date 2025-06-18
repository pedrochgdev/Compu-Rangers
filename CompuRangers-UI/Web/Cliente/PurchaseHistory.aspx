<%@ Page Title="Purchase History Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="PurchaseHistory.aspx.cs" Inherits="Web.PurchaseHistory" %>


<asp:Content ID="PruchaseHistoryContent" ContentPlaceHolderID="MainContent" runat="server">
    <body>
        <div class="container py-4">
            <h3 class="mb-4 text-center fw-bold">Historial de Compras</h3>
            <div class="row justify-content-center">
                <div class="col-12 col-md-10 col-lg-8">

                    <!-- Compra 1 -->
                    <div class="card mb-4 shadow-sm border-0 rounded-4">
                        <div class="card-body">
                            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center mb-3">
                                <div>
                                    <span class="fw-semibold">Pedido #10234</span>
                                    <span class="badge bg-success ms-2">Entregado</span>
                                </div>
                                <span class="text-muted small mt-2 mt-md-0">
                                    <i class="bi bi-calendar3"></i> 20 mayo 2024
                                </span>
                            </div>
                            <div>
                                <table class="table mb-2 table-borderless align-middle">
                                    <thead class="table-light">
                                    <tr>
                                        <th class="ps-0">Producto</th>
                                        <th class="text-center">Cant.</th>
                                        <th class="text-end pe-0">Subtotal</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td class="ps-0">Intel Core i7-12700K</td>
                                        <td class="text-center">1</td>
                                        <td class="text-end pe-0">$330.00</td>
                                    </tr>
                                    <tr>
                                        <td class="ps-0">RAM Kingston 16GB DDR4</td>
                                        <td class="text-center">2</td>
                                        <td class="text-end pe-0">$90.00</td>
                                    </tr>
                                    <tr>
                                        <td class="ps-0">SSD Samsung 970 EVO 1TB</td>
                                        <td class="text-center">1</td>
                                        <td class="text-end pe-0">$110.00</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="d-flex justify-content-end">
                                <div>
                                    <div class="fw-bold fs-5 text-success">Total: $530.00</div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Compra 2 -->
                    <div class="card mb-4 shadow-sm border-0 rounded-4">
                        <div class="card-body">
                            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center mb-3">
                                <div>
                                    <span class="fw-semibold">Pedido #10189</span>
                                    <span class="badge bg-warning text-dark ms-2">En camino</span>
                                </div>
                                <span class="text-muted small mt-2 mt-md-0">
                                    <i class="bi bi-calendar3"></i> 12 mayo 2024
                                </span>
                            </div>
                            <div>
                                <table class="table mb-2 table-borderless align-middle">
                                    <thead class="table-light">
                                    <tr>
                                        <th class="ps-0">Producto</th>
                                        <th class="text-center">Cant.</th>
                                        <th class="text-end pe-0">Subtotal</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td class="ps-0">NVIDIA RTX 4060</td>
                                        <td class="text-center">1</td>
                                        <td class="text-end pe-0">$370.00</td>
                                    </tr>
                                    <tr>
                                        <td class="ps-0">Fuente Corsair 650W</td>
                                        <td class="text-center">1</td>
                                        <td class="text-end pe-0">$78.00</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="d-flex justify-content-end">
                                <div>
                                    <div class="fw-bold fs-5 text-success">Total: $448.00</div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Compra 3 -->
                    <div class="card mb-4 shadow-sm border-0 rounded-4">
                        <div class="card-body">
                            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center mb-3">
                                <div>
                                    <span class="fw-semibold">Pedido #10077</span>
                                    <span class="badge bg-danger ms-2">Cancelado</span>
                                </div>
                                <span class="text-muted small mt-2 mt-md-0">
                                    <i class="bi bi-calendar3"></i> 28 abril 2024
                                </span>
                            </div>
                            <div>
                                <table class="table mb-2 table-borderless align-middle">
                                    <thead class="table-light">
                                    <tr>
                                        <th class="ps-0">Producto</th>
                                        <th class="text-center">Cant.</th>
                                        <th class="text-end pe-0">Subtotal</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td class="ps-0">ASUS B550-PLUS</td>
                                        <td class="text-center">1</td>
                                        <td class="text-end pe-0">$125.00</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="d-flex justify-content-end">
                                <div>
                                    <div class="fw-bold fs-5 text-success">Total: $125.00</div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Puedes duplicar más compras aquí -->

                </div>
            </div>
        </div>
    </body>
</asp:Content>
