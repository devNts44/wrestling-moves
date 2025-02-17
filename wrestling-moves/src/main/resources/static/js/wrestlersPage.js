document.addEventListener('DOMContentLoaded', function() {
    // Animation des cartes au chargement
    const cards = document.querySelectorAll('.wrestler-card');
    cards.forEach((card, index) => {
        card.style.animationDelay = `${index * 0.1}s`;
    });

    // Recherche en temps réel
    const searchInput = document.querySelector('.search-bar input');
    searchInput.addEventListener('input', function(e) {
        const searchTerm = e.target.value.toLowerCase();
        const cards = document.querySelectorAll('.wrestler-card');
        
        cards.forEach(card => {
            const wrestlerName = card.querySelector('h3').textContent.toLowerCase();
            const wrestlerId = card.querySelector('.wrestler-id').textContent.toLowerCase();
            
            if (wrestlerName.includes(searchTerm) || wrestlerId.includes(searchTerm)) {
                card.style.display = 'block';
            } else {
                card.style.display = 'none';
            }
        });
    });

    // Effet hover sur les boutons
    const buttons = document.querySelectorAll('.btn');
    buttons.forEach(button => {
        button.addEventListener('mouseover', function() {
            this.style.transform = 'scale(1.1)';
        });
        
        button.addEventListener('mouseout', function() {
            this.style.transform = 'scale(1)';
        });
    });

    // Navigation active
    const navItems = document.querySelectorAll('.nav-links li');
    navItems.forEach(item => {
        item.addEventListener('click', function() {
            navItems.forEach(i => i.classList.remove('active'));
            this.classList.add('active');
        });
    });
});