import {useState, useEffect } from  'react'
import "./App.css"

function App() {
    const [planets, setPlanets] = useState([])

    useEffect(() => {
        fetch("http://localhost:8080/api/planets/top-10")
            .then(response => response.json())
            .then(data => {
                console.log("Data received: " , data)
                setPlanets(data)
            })

    }, []);

    return (
        <div className="container">
            <h1>🪐 Exoplanet Explorer</h1>
            <p>Found {planets.length} habitable candidates.</p>

            <div className="card-grid">
                {planets.map(planet => (
                    <div key={planet.id} className="planet-card">
                        <h2>{planet.name}</h2>
                        <div className="stats">
                            <p><strong>ESI:</strong> {planet.esiScore.toFixed(3)}</p>
                            <p><strong>Radius:</strong> {planet.radius} x Earth</p>
                            <p><strong>Year Discovered:</strong> {planet.discoveryYear}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
    
}

export default App