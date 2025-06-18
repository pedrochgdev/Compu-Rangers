function showAlert(message, type = 'success', delay = 4000) {
    const container = document.getElementById('alert-container');
    const alertId = `alert-${Date.now()}`;

    const alert = document.createElement('div');
    alert.id = alertId;
    alert.className = `alert alert-${type} alert-dismissible fade show shadow`;
    alert.role = 'alert';
    alert.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        `;

    container.appendChild(alert);

    // Auto cerrar después de `delay` ms
    setTimeout(() => {
        const alertElement = document.getElementById(alertId);
        if (alertElement) {
            bootstrap.Alert.getOrCreateInstance(alertElement).close();
        }
    }, delay);
}