<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestion des Étudiants</title>
    <style>
        body { font-family: Arial; margin: 40px; background: #f9f9f9; }
        h1, h2 { color: #2c3e50; }
        form { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); margin-bottom: 20px; }
        input, button { padding: 10px; margin: 5px; border: 1px solid #ddd; border-radius: 4px; }
        button { background: #3498db; color: white; cursor: pointer; }
        button:hover { background: #2980b9; }
        .btn-edit { background: #f39c12; }
        .btn-edit:hover { background: #e67e22; }
        .btn-delete { background: #e74c3c; }
        .btn-delete:hover { background: #c0392b; }
        .btn-cancel { background: #95a5a6; }
        .btn-cancel:hover { background: #7f8c8d; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #eee; }
        th { background: #34495e; color: white; }
        tr:hover { background: #f5f5f5; }
        .actions { white-space: nowrap; }
        .edit-form { background: #fff3cd; border: 1px solid #ffeaa7; }
    </style>
</head>
<body>
<!-- Messages d'erreur et de succès -->
<c:if test="${not empty error}">
    <div style="background: #f8d7da; color: #721c24; padding: 15px; margin: 20px 0; border: 1px solid #f5c6cb; border-radius: 4px;">
        ❌ ${error}
    </div>
</c:if>

<c:if test="${not empty success}">
    <div style="background: #d4edda; color: #155724; padding: 15px; margin: 20px 0; border: 1px solid #c3e6cb; border-radius: 4px;">
        ✅ ${success}
    </div>
</c:if>
<h1>Gestion des Étudiants</h1>

<!-- Formulaire d'ajout/modification -->
<div class="${not empty etudiantEdit ? 'edit-form' : ''}">
    <h2>
        <c:choose>
            <c:when test="${not empty etudiantEdit}">
                ✏️ Modifier l'Étudiant (ID: ${etudiantEdit.idetudiant})
            </c:when>
            <c:otherwise>
                ➕ Ajouter un étudiant
            </c:otherwise>
        </c:choose>
    </h2>

    <form action="etudiants" method="post">
        <c:if test="${not empty etudiantEdit}">
            <input type="hidden" name="id" value="${etudiantEdit.idetudiant}">
            <input type="hidden" name="action" value="update">
        </c:if>
        <c:if test="${empty etudiantEdit}">
            <input type="hidden" name="action" value="add">
        </c:if>

        <input placeholder="Nom" name="nom" value="${etudiantEdit.nom}" required>
        <input placeholder="Prénom" name="prenom" value="${etudiantEdit.prenom}" required>
        <input placeholder="CNE" name="cne" value="${etudiantEdit.cne}" required>
        <input placeholder="Adresse" name="adresse" value="${etudiantEdit.adresse}">
        <input placeholder="Niveau" name="niveau" value="${etudiantEdit.niveau}">

        <button type="submit">
            <c:choose>
                <c:when test="${not empty etudiantEdit}">💾 Modifier</c:when>
                <c:otherwise>✅ Ajouter</c:otherwise>
            </c:choose>
        </button>

        <c:if test="${not empty etudiantEdit}">
            <a href="etudiants" class="btn-cancel" style="padding: 10px; text-decoration: none; border-radius: 4px;">❌ Annuler</a>
        </c:if>
    </form>
</div>

<!-- Liste des étudiants -->
<h2>📋 Liste des étudiants</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>CNE</th>
        <th>Adresse</th>
        <th>Niveau</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${etudiants}" var="e">
        <tr>
            <td>${e.idetudiant}</td>
            <td>${e.nom}</td>
            <td>${e.prenom}</td>
            <td>${e.cne}</td>
            <td>${e.adresse}</td>
            <td>${e.niveau}</td>
            <td class="actions">
                <!-- Lien pour modifier -->
                <a href="etudiants?action=edit&id=${e.idetudiant}"
                   style="background: #f39c12; color: white; padding: 5px 10px; text-decoration: none; border-radius: 3px; margin-right: 5px;">
                    ✏️ Modifier
                </a>

                <!-- Formulaire pour supprimer -->
                <form action="etudiants" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${e.idetudiant}">
                    <button type="submit" class="btn-delete"
                            onclick="return confirm('Êtes-vous sûr de vouloir supprimer étudiant ${e.nom} ${e.prenom} ?')"
                            style="padding: 5px 10px; font-size: 12px;">
                        🗑️ Supprimer
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Message si aucun étudiant -->
<c:if test="${empty etudiants}">
    <p style="text-align: center; color: #7f8c8d; margin-top: 20px;">
        Aucun étudiant trouvé. Ajoutez le premier étudiant !
    </p>
</c:if>
</body>
</html>