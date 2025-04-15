/*
  But :    Projet
  Auteur : Nathan Borgeat
  Date :   28.06.2024 / V1.0 
*/

const BASE_URL = "https://astroherisson.borgeatn.emf-informatique.ch/apiGateway/";
// const BASE_URL = "http://localhost:8080/apiGateway/";

class ServiceHttp {
  /**
   * Essaye de connecter l'utilisateur via une requête POST.
   *
   * @param {String} login login de l'utilisateur
   * @param {String} password mot de passe de l'utilisateur (ou le mot qui permet de passer :) )
   * @param {Function} successCallback la fonction appelée en cas de succès
   * @param {Function} errorCallback la fonction appelée en cas d'erreur
   */
  connecteLogin(login, password, successCallback, errorCallback) {
    $.ajax({
      xhrFields: {
        withCredentials: true,
      },
      crossDomain: true,
      type: "POST",
      dataType: "text",
      url: BASE_URL + "login",
      data: {
        username: login,
        password: password,
      },
      success: successCallback,
      error: errorCallback,
    });
  }

  /**
   * Essaye de créer un nouvel utilisateur.
   *
   * @param {String} login le nouveau login de l'utilisateur
   * @param {String} password le nouveau mot de passe de l'utilisateur (ou le mot qui permet de passer :) )
   * @param {Function} successCallback la fonction appelée en cas de succès
   * @param {Function} errorCallback la fonction appelée en cas d'erreur
   */
  creeLogin(login, password, successCallback, errorCallback) {
    $.ajax({
      type: "POST",
      dataType: "text",
      url: BASE_URL + "signin",
      data: {
        username: login,
        password: password,
      },
      success: successCallback,
      error: errorCallback,
    });
  }

  /**
   * Essaye de déconnecter l'utilisateur
   *
   * @param {Function} successCallback la fonction appelée en cas de succès
   * @param {Function} errorCallback la fonction appelée en cas d'erreur
   */
  deconnecteLogin(successCallback, errorCallback) {
    $.ajax({
      xhrFields: {
        withCredentials: true,
      },
      crossDomain: true,
      type: "POST",
      dataType: "text",
      url: BASE_URL + "logout",
      success: successCallback,
      error: errorCallback,
    });
  }

  getHerissons(successCallback, errorCallback) {
    $.ajax({
      xhrFields: {
        withCredentials: true,
      },
      crossDomain: true,
      type: "GET",
      dataType: "json",
      url: BASE_URL + "getHerissons",
      success: successCallback,
      error: errorCallback,
    });
  }

  getVoyages(successCallback, errorCallback) {
    $.ajax({
      xhrFields: {
        withCredentials: true,
      },
      crossDomain: true,
      type: "GET",
      dataType: "json",
      url: BASE_URL + "getVoyages",
      success: successCallback,
      error: errorCallback,
    });
  }

  getFusees(successCallback, errorCallback) {
    $.ajax({
      xhrFields: {
        withCredentials: true,
      },
      crossDomain: true,
      type: "GET",
      dataType: "json",
      url: BASE_URL + "getFusee",
      success: successCallback,
      error: errorCallback,
    });
  }

  addHerisson(nom, caracteristique, successCallback, errorCallback) {
    $.ajax({
      xhrFields: {
        withCredentials: true,
      },
      crossDomain: true,
      type: "POST",
      dataType: "text",
      url: BASE_URL + "addHerisson",
      data: {
        nom: nom,
        caracteristique: caracteristique
      },
      success: successCallback,
      error: errorCallback,
    });
  }

  putHerisson(pk, nom, caracteristique, successCallback, errorCallback) {
    $.ajax({
      xhrFields: {
        withCredentials: true,
      },
      crossDomain: true,
      type: "PUT",
      dataType: "text",
      url: BASE_URL + "putHerisson",
      data: {
        pk_herisson: pk,
        nom: nom,
        caracteristique: caracteristique
      },
      success: successCallback,
      error: errorCallback,
    });
  }

  addVoyage(destination, herisson, fusee, successCallback, errorCallback) {
    $.ajax({
      xhrFields: {
        withCredentials: true,
      },
      crossDomain: true,
      type: "POST",
      dataType: "text",
      url: BASE_URL + "addVoyage",
      data: {
        destination: destination,
        herissonId: herisson,
        fk_fusee: fusee
      },
      success: successCallback,
      error: errorCallback,
    });
  }
}
