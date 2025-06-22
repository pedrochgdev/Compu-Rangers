<%@ Page Title="Success Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Success.aspx.cs" Inherits="Web.Cliente.PaymentResponse.Success" %>

<asp:Content ID="SuccessPayment" ContentPlaceHolderID="MainContent" runat="server">
    <div style="text-align:center; padding:50px;">
        <h2 style="color: #444;">⏳ Esperando confirmación del pago...</h2>
        <p>Pago Completado</p>
        <img src="~/images/success.gif" alt="Completado" style="margin-top:20px;" />
    </div>

    <script type="text/javascript">
        // Notifica a la ventana que abrió esta que el pago ha sido completado
        if (window.opener) {
            window.opener.postMessage('pago-completado', '*');
        }

        // Espera 5 segundos y cierra la ventana
        setTimeout(function () {
            window.close();
        }, 5000);
    </script>
</asp:Content>

