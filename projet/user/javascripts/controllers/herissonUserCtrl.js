/*
  But :    Projet 151 : Star Rail
  Auteur : Nathan Borgeat
  Date :   28.02.2025 / V1.0 
*/

class HerissonUserCtrl {
  constructor() {
    $("#deconnexion").click(() => {
      this.logout();
    });

    $("#nouveauherisson").click(() => {
      this.nouveauHerisson();
    });

    $("#modifierherisson").click(() => {
      this.modifierHerisson();
    });

    $("#nouveauvoyage").click(() => {
      this.nouveauVoyage();
    });

    this.getHerissons();
    this.getFusees();
    this.getVoyages();
  }

  /**
   * Obtient les herissons de l'utilisateur et remplit les selects et le tableau correspondants
   */
  getHerissons() {
    // Efface les éventuels hérissons déjà présents
    $("#herissons").find("option:not(:first)").remove();
    $("#herissonsvoyage").find("option:not(:first)").remove();
    $("#tableherisson").find("tbody tr:not(:last)").remove();

    serviceHttp.getHerissons((data) => {
      let selectHerisson1 = $("#herissons");
      let selectHerisson2 = $("#herissonsvoyage");

      data.sort((a, b) => a.name.localeCompare(b.name));

      data.forEach(
        (herisson) => {
          let option1 = $("<option></option>");
          let option2 = $("<option></option>");
          option1.val(herisson.id);
          option1.text(herisson.name);
          option2.val(herisson.id);
          option2.text(herisson.name);
          selectHerisson1.append(option1);
          selectHerisson2.append(option2);
        },
        () => {
          console.log("getHerissons error");
        }
      );

      data.sort((a, b) => b.name.localeCompare(a.name));

      data.forEach(
        (herisson) => {
          $("#tableherisson tbody").prepend("<tr><td>" + herisson.name + "</td><td>" + herisson.caracteristique + "</td></tr>");
        },
        () => {
          console.log("getHerissons error");
        }
      );

      $("#nomherisson").val("");
      $("#caractetistiqueherisson").val("");
    });
  }

  /**
   * Obtient les voyages et remplit le tableau correspondant
   */
  getVoyages() {
    serviceHttp.getFusees((fusees) => {
      $("#tablevoyage").find("tbody tr:not(:last)").remove();

      serviceHttp.getVoyages((data) => {
        let nomFusee = "";

        data.sort((a, b) => b.destination.localeCompare(a.destination));

        data.forEach((voyage) => {

          let fusee = fusees.find(f => f.id == voyage.fusee);
          let nomFusee = fusee ? fusee.nom : "";

          $("#tablevoyage").prepend("<tr><td>" + voyage.destination + "</td><td>" + voyage.herisson.name + "</td><td>" + nomFusee + "</td></tr>");
        });
      });

      $("#destinationvoyage").val("");
      $("#herissonsvoyage").prop("selectedIndex", 0);
      $("#fuseesvoyage").prop("selectedIndex", 0);
      // $("#fuseesvoyage").val(1);
    });
  }

  /**
   * Obtient les fusees et remplit le select dans le tableau voyage
   */
  getFusees() {
    serviceHttp.getFusees((data) => {

      data.sort((a, b) => a.nom.localeCompare(b.nom));

      data.forEach((fusee) => {
        let option = $("<option></option>").val(fusee.id).text(fusee.nom);

        $("#fuseesvoyage").append(option);
      });
    });
  }

  /**
   * Déconnecte l'utilisateur courant et load la page de login
   */
  logout() {
    serviceHttp.deconnecteLogin(
      () => {
        indexCtrl.loadLogin();
      },
      () => {
        console.log("logout error");
      }
    );
  }

  /**
   * Crée un nouveau hérisson avec les valeurs entrées
   */
  nouveauHerisson() {
    let nom = $("#nomherisson").val();
    let caracteristique = $("#caractetistiqueherisson").val();
    if (
      nom == "" ||
      caracteristique == ""
    ) {
      window.alert(
        "Veuillez remplir le champ du nom et et de la caractéristique"
      );
    } else {
      serviceHttp.addHerisson(
        nom,
        caracteristique,
        () => {
          window.alert("Le hérisson a bien été ajouté !");
          this.getHerissons();
        },
        () => {
          window.alert(
            "Le hérisson n'a pas pu être ajouté"
          );
          console.log("nouveauHerisson error");
        }
      );
    }
  }

  /**
   * Modifie la team sélectionnée avec les valeurs sélectionnés / entrées
   */
  modifierHerisson() {
    let pk = $("#herissons").val();
    let nom = $("#nomherisson").val();
    let caracteristique = $("#caractetistiqueherisson").val();

    if (
      nom == "" ||
      caracteristique == "" ||
      pk == ""
    ) {
      window.alert(
        "Veuillez remplir les champs et sélectionner un hérisson"
      );
    } else {
      serviceHttp.putHerisson(
        pk,
        nom,
        caracteristique,
        () => {
          window.alert("Le hérisson a bien été modifié");
          this.getHerissons();
        },
        () => {
          window.alert(
            "Le hérisson n'a pas pu être modifié"
          );
          console.log("modifierHerisson error");
        }
      );
    }
  }

  /**
   * Crée un nouveau voyage avec les valeurs entrées
   */
  nouveauVoyage() {
    let destination = $("#destinationvoyage").val();
    let herisson = $("#herissonsvoyage").val();
    let fusee = $("#fuseesvoyage").val();;
    if (
      destination == "" ||
      herisson == "" ||
      fusee == ""
    ) {
      window.alert(
        "Veuillez remplir tous les champs et sélectionner des options valides"
      );
    } else {
      serviceHttp.addVoyage(
        destination,
        herisson,
        fusee,
        () => {
          window.alert("Le voyage a bien été ajouté !");
          this.getVoyages();
        },
        () => {
          window.alert(
            "Le voyage n'a pas pu être ajouté"
          );
          console.log("nouveauVoyage error");
        }
      );
    }
  }
}
