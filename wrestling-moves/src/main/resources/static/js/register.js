(function() {
   document.querySelector("form").addEventListener("submit", function(event) {
       const password = document.querySelector("input[name='password']").value;
       const confirmPassword = document.querySelector("input[name='confirmPassword']").value;
       if (password !== confirmPassword) {
           alert("Les mots de passe ne correspondent pas.");
           event.preventDefault();
       }
   });
})();
