document.addEventListener('DOMContentLoaded', function() {
    fetchEntities('movies');
    fetchEntities('customers');
    fetchEntities('rentals');
    fetchEntities('genres');
});

function fetchEntities(entity) {
    fetch(`/${entity}`)
        .then(response => response.json())
        .then(data => displayEntities(entity, data))
        .catch(error => console.error(`Error loading ${entity}:`, error));
}

function displayEntities(entity, items) {
    const container = document.getElementById(entity);
    container.innerHTML = '';
    items.forEach(item => {
        const itemElement = document.createElement('div');
        itemElement.className = 'item';
        itemElement.innerHTML = Object.values(item).map(value => `<span>${value}</span>`).join(' ') +
            `<button onclick="deleteEntity('${entity}', '${item.id}')">Delete</button>`;
        container.appendChild(itemElement);
    });
}

function submitEntity(event, entity, data) {
    event.preventDefault();
    fetch(`/${entity}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(() => {
        fetchEntities(entity);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function deleteEntity(entity, id) {
    fetch(`/${entity}/${id}`, {
        method: 'DELETE'
    })
    .then(() => {
        fetchEntities(entity);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

// submission functions
function submitMovie(event) {
    const data = {
        title: document.getElementById('movie-title').value,
        director: document.getElementById('movie-director').value,
        genre: { name: document.getElementById('movie-genre').value }
    };
    submitEntity(event, 'movies', data);
}
