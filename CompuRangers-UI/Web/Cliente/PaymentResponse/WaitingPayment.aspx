<%@ Page Title="Waiting Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="WaitingPayment.aspx.cs" Inherits="Web.Cliente.PaymentResponse.WaitingPayment" %>

<asp:Content ID="WaitingPayment" ContentPlaceHolderID="MainContent" runat="server">
    <div style="text-align:center; padding:50px;">
        <h2 style="color: #444;">⏳ Esperando confirmación del pago...</h2>
        <p>Por favor, no cierres esta ventana. El proceso puede tardar unos segundos.</p>
        <img src="~/images/loading.gif" alt="Cargando..." style="margin-top:20px;" />

        <script type="text/javascript">
            // Escucha cuando la ventana de pago confirme el pago
            window.addEventListener('message', function (event) {
                if (event.data === 'pago-completado') {
                    window.location.href = '../../Catalogo/Home.aspx'; // Redirige a donde tú desees
                }
            });
        </script>
    </div>
</asp:Content>
