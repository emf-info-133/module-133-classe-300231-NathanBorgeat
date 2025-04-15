/*
  But :    Projet 151 : Star Rail
  Auteur : Nathan Borgeat
  Date :   28.02.2025 / V1.0 
*/

class SignInCtrl {
  constructor() {
    $("#signinbutton").click(() => {
      this.signin();
    });
  }

  /**
   * Crée un nouvel utilisateur et charge la page de login
   */ 
  signin() {
    let newlogin = $("#newlogin").val();
    let password = $("#newpassword").val();
    let confirmpassword = $("#confirmpassword").val();

    if (newlogin != "" && password != "" && confirmpassword != "") {
      if (password == confirmpassword) {
        serviceHttp.creeLogin(
          newlogin,
          password,
          () => {
            window.alert(
              "Votre compte a bien été créé ! Veuillez maintenant vous connecter."
            );
            indexCtrl.loadLogin();
          },
          () => {
            alert("Login déja existant");
            console.log("signin error");
          }
        );
      } else {
        alert("Les mots de passe de correspondent pas, veuillez réessayer.");
      }
    } else {
      alert("Veuillez compléter tous les champs");
    }
  }
}
