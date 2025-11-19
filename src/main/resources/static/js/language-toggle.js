(function(){
    function initLanguageToggle(){
        var toggleSwitch = document.getElementById('languageSwitch');
        if (!toggleSwitch) return;
        
        // Get current language from URL parameter or default to 'en'
        var urlParams = new URLSearchParams(window.location.search);
        var currentLang = urlParams.get('lang') || 'en';
        
        // Set toggle state based on current language
        if (currentLang === 'ja') {
            toggleSwitch.checked = true;
        }
        
        // Handle toggle change
        toggleSwitch.addEventListener('change', function () {
            var newLang = this.checked ? 'ja' : 'en';
            var currentUrl = window.location.pathname;
            window.location.href = currentUrl + '?lang=' + newLang;
        });
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', initLanguageToggle);
    } else {
        initLanguageToggle();
    }
})();
