
<c:if test="${not empty error}">
    <div class="validation-error">
        <input type="checkbox" id="close-error" class="error-switch">
        <div class="error-banner">
            <h1 class="validation-error__title">Ошибка</h1>
            <p class="error-banner__text">${error}</p>
            <label for="close-error" class="error-banner__close">Закрыть
            </label>
        </div>
    </div>
</c:if>
