// Conseils aléatoires
const tips = [
    "🎯 Concentrez-vous sur la qualité des prises plutôt que la quantité",
    "📊 Analysez toujours vos matchs précédents pour progresser",
    "💧 N'oubliez pas de bien vous hydrater pendant l'entraînement",
    "⚖️ Travaillez votre équilibre avec des exercices spécifiques",
    "🔄 Variez vos techniques d'échauffement",
    "🧘 La récupération est aussi importante que l'entraînement",
    "💪 Maintenez une routine d'entraînement régulière",
    "🎯 Fixez-vous des objectifs réalisables à court terme"
];

function showDailyTip() {
    const tipElement = document.getElementById('dailyTip');
    const randomTip = tips[Math.floor(Math.random() * tips.length)];
    tipElement.style.opacity = '0';
    
    setTimeout(() => {
        tipElement.textContent = randomTip;
        tipElement.style.opacity = '1';
    }, 200);
}

document.querySelector('.close-tip').addEventListener('click', () => {
    const tipContainer = document.querySelector('.daily-tip');
    tipContainer.style.opacity = '0';
    setTimeout(() => {
        tipContainer.style.display = 'none';
    }, 300);
});

// Initialisation
document.addEventListener('DOMContentLoaded', () => {
    showDailyTip();
    
    // Changer le conseil toutes les 24 heures
    setInterval(showDailyTip, 24 * 60 * 60 * 1000);
});