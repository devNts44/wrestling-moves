document.addEventListener('DOMContentLoaded', function() {
    // Animation au défilement
    window.addEventListener('scroll', animateCards);

    // Initialisation
    animateCards();
    setRandomTip();
    initHoverEffects();
});

function animateCards() {
    document.querySelectorAll('.stat-card').forEach(card => {
        const position = card.getBoundingClientRect();
        if (position.top < window.innerHeight - 100) {
            card.classList.add('animate__animated', 'animate__fadeInUp');
        }
    });
}

function setRandomTip() {
    const tips = [
        "Visualisez vos mouvements avant de les exécuter pour améliorer la précision.",
        "Maintenez un journal d'entraînement pour suivre vos progrès.",
        "Travaillez votre souplesse 15 minutes par jour pour prévenir les blessures.",
        "Analysez vos matchs passés pour identifier les points à améliorer.",
        "Hydratez-vous régulièrement pendant les sessions d'entraînement."
    ];

    const tipElement = document.getElementById('dailyTip');
    if (tipElement) {
        tipElement.textContent = tips[Math.floor(Math.random() * tips.length)];
    }
}

function initHoverEffects() {
    document.querySelectorAll('.action-btn').forEach(btn => {
        btn.addEventListener('mouseenter', () => {
            btn.style.transform = 'scale(1.05)';
        });
        btn.addEventListener('mouseleave', () => {
            btn.style.transform = 'scale(1)';
        });
    });
}