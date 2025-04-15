/*
  But :    Projet 151 : Star Rail
  Auteur : Nathan Borgeat
  Date :   28.02.2025 / V1.0 
*/

class LoginCtrl {
  constructor() {
    $("#loginbutton").click(() => {
      this.login();
    });
  }

  /**
   * Connecte l'utilisateur et charge la page principale
   */
  login() {
    let login = $("#login").val();
    let password = $("#password").val();

    if (login != "" && password != "") {
      serviceHttp.connecteLogin(
        login,
        password,
        () => {
          indexCtrl.loadHerissonUser();
        },
        () => {
          alert("Login invalide");
          console.log("login error");
        }
      );
    } else {
      alert("Veuillez remplir les deux champs");
    }
  }
}
