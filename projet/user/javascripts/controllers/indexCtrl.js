/*
  But :    Projet 151 : Star Rail
  Auteur : Nathan Borgeat
  Date :   28.02.2025 / V1.0 
*/

/**
 * Initialise le indexCtrl et le serviceHttp
 */
$(document).ready(function () {
  serviceHttp = new ServiceHttp(); // service http
  indexCtrl = new IndexCtrl(); // ctrl principal
});

class IndexCtrl {
  /**
   * Charge la page principale si l'utilisateur a déjà une session, sinon load la page de login
   */
  constructor() {
    this.loadLogin();
  }

  /**
   * Charge la vue "login" et initialise la classe "LoginCtrl"
   */
  loadLogin() {
    this.chargerVue("login", () => new LoginCtrl());
  }

  /**
   * Charge la vue "signin" et initialise la classe "SignInCtrl"
   */
  loadSignIn() {
    this.chargerVue("signin", () => new SignInCtrl());
  }

  /**
   * Charge la vue "teammaker" et initialise la classe "TeamMakerCtrl"
   */
  loadHerissonUser() {
    this.chargerVue("herissonUser", () => new HerissonUserCtrl());
  }

  /**
   * Charge la vue en paramètre et appelle le callback s'il existe
   */
  chargerVue(vue, callback) {
    $("#view").load("views/" + vue + ".html", function () {
      if (typeof callback !== "undefined") {
        callback();
      }
    });
  }
}
