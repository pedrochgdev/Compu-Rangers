<%@ Page Title="Cancel Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Cancel.aspx.cs" Inherits="Web.Cliente.PaymentResponse.Cancel" %>

<asp:Content ID="CancelPayment" ContentPlaceHolderID="MainContent" runat="server">
    <div style="text-align:center; padding:50px;">
        <h2 style="color: #444;">⏳ Esperando confirmación del pago...</h2>
        <p>Pago Cancelado</p>
        <img src="~/images/success.gif" alt="Completado" style="margin-top:20px;" />
    </div>
</asp:Content>
