document.querySelectorAll('[data-modal-toggle]').forEach((btn) => {
    btn.addEventListener('click', function () {
        const modalId = this.getAttribute('data-modal-target');
        const modal = document.getElementById(modalId);

        modal.classList.remove('hidden');
        const deleteUrl = this.getAttribute('data-delete-url');
        document.getElementById('confirmDeleteButton').setAttribute('href', deleteUrl);
    });
});

document.querySelectorAll('[data-modal-hide]').forEach((btn) => {
    btn.addEventListener('click', function () {
        this.closest('.fixed').classList.add('hidden');
    });
});


document.addEventListener('DOMContentLoaded', function () {
    // Sélection des éléments HTML
    const incrementButton = document.getElementById('increment-button');
    const decrementButton = document.getElementById('decrement-button');
    const quantityInput = document.getElementById('quantity-input');

    // Récupération des valeurs min et max
    const minValue = parseInt(quantityInput.getAttribute('data-input-counter-min')) || 1;
    const maxValue = parseInt(quantityInput.getAttribute('data-input-counter-max')) || 100;

    // Fonction pour incrémenter
    incrementButton.addEventListener('click', function () {
        let currentValue = parseInt(quantityInput.value) || 0;
        if (currentValue < maxValue) {
            quantityInput.value = currentValue + 1;
        }
    });

    // Fonction pour décrémenter
    decrementButton.addEventListener('click', function () {
        let currentValue = parseInt(quantityInput.value) || 0;
        if (currentValue > minValue) {
            quantityInput.value = currentValue - 1;
        }
    });
});