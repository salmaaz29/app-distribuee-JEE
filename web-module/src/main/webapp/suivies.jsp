<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Gestion des Notes</title>
    <style>
        body { font-family: Arial; margin: 40px; background: #f9f9f9; }
        h1, h2 { color: #2c3e50; }
        form { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); margin-bottom: 20px; }
        select, input, button { padding: 10px; margin: 5px; border: 1px solid #ddd; border-radius: 4px; width: 100%; max-width: 300px; }
        button { background: #e74c3c; color: white; cursor: pointer; }
        button:hover { background: #c0392b; }
        .btn-edit { background: #f39c12; }
        .btn-edit:hover { background: #e67e22; }
        .btn-delete { background: #e74c3c; }
        .btn-delete:hover { background: #c0392b; }
        .btn-cancel { background: #95a5a6; }
        .btn-cancel:hover { background: #7f8c8d; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #eee; }
        th { background: #c0392b; color: white; }
        tr:hover { background: #f5f5f5; }
        .actions { white-space: nowrap; }
        .edit-form { background: #fff3cd; border: 1px solid #ffeaa7; }
        .message { padding: 15px; margin: 20px 0; border-radius: 4px; }
        .error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
        .success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
    </style>
</head>
<body>
<h1>Gestion des Notes</h1>

<!-- Messages d'erreur et de succès -->
<c:if test="${not empty error}">
    <div class="message error">
        ❌ ${error}
    </div>
</c:if>

<c:if test="${not empty success}">
    <div class="message success">
        ✅ ${success}
    </div>
</c:if>

<!-- Formulaire d'ajout/modification -->
<div class="${not empty suivieEdit ? 'edit-form' : ''}">
    <h2>
        <c:choose>
            <c:when test="${not empty suivieEdit}">
                ✏️ Modifier la Note
            </c:when>
            <c:otherwise>
                ➕ Ajouter une note
            </c:otherwise>
        </c:choose>
    </h2>

    <form action="suivies" method="post">
        <c:if test="${not empty suivieEdit}">
            <input type="hidden" name="idEtudiant" value="${suivieEdit.etudiant.idetudiant}">
            <input type="hidden" name="idModule" value="${suivieEdit.module.idmodule}">
            <input type="hidden" name="action" value="update">
        </c:if>
        <c:if test="${empty suivieEdit}">
            <input type="hidden" name="action" value="add">
        </c:if>

        <label>Étudiant :</label>
        <select name="etudiant" required <c:if test="${not empty suivieEdit}">disabled</c:if>>
            <option value="">-- Choisir --</option>
            <c:forEach items="${etudiants}" var="e">
                <option value="${e.idetudiant}"
                        <c:if test="${not empty suivieEdit && suivieEdit.etudiant.idetudiant == e.idetudiant}">selected</c:if>>
                        ${e.nom} ${e.prenom} (${e.cne})
                </option>
            </c:forEach>
        </select>

        <label>Module :</label>
        <select name="module" required <c:if test="${not empty suivieEdit}">disabled</c:if>>
            <option value="">-- Choisir --</option>
            <c:forEach items="${modules}" var="m">
                <option value="${m.idmodule}"
                        <c:if test="${not empty suivieEdit && suivieEdit.module.idmodule == m.idmodule}">selected</c:if>>
                        ${m.nommodule} (${m.codemodule})
                </option>
            </c:forEach>
        </select>

        <label>Note (0-20) :</label>
        <input type="number" step="0.01" min="0" max="20" name="note"
               value="${suivieEdit.note}" required>

        <button type="submit" style="background: <c:choose><c:when test="${not empty suivieEdit}">#f39c12</c:when><c:otherwise>#e74c3c</c:otherwise></c:choose>;">
            <c:choose>
                <c:when test="${not empty suivieEdit}">💾 Modifier</c:when>
                <c:otherwise>✅ Noter</c:otherwise>
            </c:choose>
        </button>

        <c:if test="${not empty suivieEdit}">
            <a href="suivies" class="btn-cancel" style="padding: 10px; text-decoration: none; border-radius: 4px;">❌ Annuler</a>
        </c:if>
    </form>
</div>

<!-- Historique des notes -->
<h2>📋 Historique des notes</h2>
<table>
    <tr>
        <th>Étudiant</th>
        <th>Module</th>
        <th>Note</th>
        <th>Date</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${suivies}" var="s">
        <tr>
            <td>${s.etudiant.nom} ${s.etudiant.prenom}</td>
            <td>${s.module.nommodule}</td>
            <td>${s.note}</td>
            <td><fmt:formatDate value="${s.dateNote}" pattern="dd/MM/yyyy"/></td>
            <td class="actions">
                <!-- Lien pour modifier -->
                <a href="suivies?action=edit&idModule=${s.module.idmodule}&idEtudiant=${s.etudiant.idetudiant}"
                   style="background: #f39c12; color: white; padding: 5px 10px; text-decoration: none; border-radius: 3px; margin-right: 5px;">
                    ✏️ Modifier
                </a>

                <!-- Formulaire pour supprimer -->
                <form action="suivies" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="idModule" value="${s.module.idmodule}">
                    <input type="hidden" name="idEtudiant" value="${s.etudiant.idetudiant}">
                    <button type="submit" class="btn-delete"
                            onclick="return confirm('Êtes-vous sûr de vouloir supprimer la note de ${s.etudiant.nom} ${s.etudiant.prenom} en ${s.module.nommodule} ?')"
                            style="padding: 5px 10px; font-size: 12px;">
                        🗑️ Supprimer
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Message si aucune note -->
<c:if test="${empty suivies}">
    <p style="text-align: center; color: #7f8c8d; margin-top: 20px;">
        Aucune note trouvée. Ajoutez la première note !
    </p>
</c:if>
</body>
</html>