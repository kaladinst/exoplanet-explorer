import {useState, useEffect } from  'react'
import "./App.css"

const filters = [
    { label: "🌍 Earth-Like (> 0.8)",   min: 0.8, max: 1.0, color: "#42d392" },
    { label: "🌿 Potential (0.6 - 0.8)", min: 0.6, max: 0.8, color: "#f59e0b" },
    { label: "🔥 Hostile (< 0.6)",      min: 0.0, max: 0.6, color: "#ef4444" }
];

function App() {
    const [planets, setPlanets] = useState([])

    const [selectedFilter, setSelectedFilter] = useState(null)

    useEffect(() => {
        let url = "http://localhost:8080/api/planets/top-10";

        if (selectedFilter) {
            url = `http://localhost:8080/api/planets/between?min=${selectedFilter.min}&max=${selectedFilter.max}`;
        }

        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log("Data received: " , data)
                setPlanets(data)
            })

    }, [selectedFilter]);

    return (
        <div className="container">
            <h1>🪐 Exoplanet Explorer</h1>
<div className="filter-container" style={{ marginBottom: "2rem", display: "flex", gap: "10px", justifyContent: "center", flexWrap: "wrap" }}>
                
                <button 
                    onClick={() => setSelectedFilter(null)}
                    style={{
                        backgroundColor: selectedFilter === null ? "#646cff" : "#333",
                        border: "1px solid #555"
                    }}
                >
                    ⭐ Top 10
                </button>

                {filters.map((filter) => (
                    <button
                        key={filter.label}
                        onClick={() => setSelectedFilter(filter)}
                        style={{
                            backgroundColor: selectedFilter === filter ? filter.color : "#333",
                            borderColor: filter.color, 
                            borderWidth: "1px",
                            borderStyle: "solid",
                            color: "white"
                        }}
                    >
                        {filter.label}
                    </button>
                ))}
            </div>

            <div className="card-grid">
                {planets.map(planet => (
                    <div 
                        key={planet.id} 
                        className="planet-card"
                        style={{ 
                            borderTop: `4px solid ${
                                planet.esiScore >= 0.8 ? "#42d392" : 
                                planet.esiScore >= 0.6 ? "#f59e0b" : "#ef4444"
                            }` 
                        }}
                    >
                        <h2>{planet.name}</h2>
                        <div className="stats">
                            <p><strong>ESI:</strong> {planet.esiScore.toFixed(3)}</p>
                            <p><strong>Radius:</strong> {planet.radius} x Earth</p>
                            <p><strong>Year:</strong> {planet.discoveryYear}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
    
}

export default App