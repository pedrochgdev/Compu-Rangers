<%@ Page Title="Error Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Error.aspx.cs" Inherits="Web.Cliente.PaymentResponse.Error" %>

<asp:Content ID="ErrorPayment" ContentPlaceHolderID="MainContent" runat="server">
    <div style="text-align:center; padding:50px;">
        <h2 style="color: #444;">⏳ Esperando confirmación del pago...</h2>
        <p>Error en el pago</p>
        <img src="~/images/success.gif" alt="Completado" style="margin-top:20px;" />
    </div>
</asp:Content>

