<%@ Page Title="Waiting Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="WaitingPayment.aspx.cs" Inherits="Web.Cliente.PaymentResponse.WaitingPayment" %>

<asp:Content ID="WaitingPayment" ContentPlaceHolderID="MainContent" runat="server">
    <div style="text-align:center; padding:50px;">
        <h2 style="color: #444;">⏳ Esperando confirmación del pago...</h2>
        <p>Por favor, no cierres esta ventana. El proceso puede tardar unos segundos.</p>
        <img src="~/images/loading.gif" alt="Cargando..." style="margin-top:20px;" />

        <script type="text/javascript">
            window.addEventListener('message', function (event) {
                if (event.data && event.data.tipo === 'pago-completado') {
                    const id = event.data.idOrden;
                    const urlBoleta = `${window.BaseUrl}/Reportes/ordenes?id=${id}`;

                    // Abre el reporte en una nueva pestaña
                    window.open(urlBoleta, '_blank');

                    // Redirige esta página (WaitingPayment) al Home
                    window.location.href = '../../Catalogo/Home.aspx';
                } else if (event.data && event.data.pago === 'cancelado') {
                    window.location.href = '../../Cliente/DetalleCompra.aspx';
                }
            });
        </script>
    </div>
</asp:Content>
