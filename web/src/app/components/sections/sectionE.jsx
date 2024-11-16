"use client";

import { useEffect } from "react";

const mapKey = process.env.NEXT_PUBLIC_MAP_KEY;

export const SectionE = () => {
    useEffect(() => {
        if (!mapKey) {
            console.error("Google Maps API key is missing!");
            return;
        }

        const loadGoogleMapsScript = () => {
            return new Promise((resolve, reject) => {
                if (window.google) {
                    resolve(); // La API ya está cargada
                    return;
                }

                const script = document.createElement("script");
                script.src = `https://maps.googleapis.com/maps/api/js?key=${mapKey}&libraries=marker`;
                script.async = true;
                script.defer = true;
                script.onload = resolve; // Resuelve la promesa cuando se carga
                script.onerror = reject; // Rechaza la promesa si falla la carga
                document.head.appendChild(script);
            });
        };

        const initMap = () => {
            const locations = [
                { lat: -34.914506971122385, lng: -57.95116686002905, title: "Cinema Rocha" },
                { lat: -34.916811941489954, lng: -57.95247577789763, title: "Cinema City" },
                { lat: -34.915721047551486, lng: -57.956788769563, title: "Cinema Paraiso" },
                { lat: -34.91679434654146, lng: -57.949321500411024, title: "Cinema Ocho" },
                { lat: -34.91494685596094, lng: -57.94936441575099, title: "Cinema San Martin" },
            ];

            const map = new google.maps.Map(document.getElementById("map"), {
                zoom: 16,
                center: { lat: -34.914506971122385, lng: -57.95116686002905 },
            });

            locations.forEach((location) => {
                new google.maps.marker.AdvancedMarkerElement({
                    map: map,
                    position: { lat: location.lat, lng: location.lng },
                    title: location.title,
                });
            });
        };

        loadGoogleMapsScript()
            .then(() => {
                initMap();
            })
            .catch((err) => {
                console.error("Error al cargar la API de Google Maps:", err);
            });
    }, []);

    return (
        <section className="w-full py-12 md:py-24 lg:py-32 bg-gray-200 dark:bg-gray-200">
            <div className="px-4 md:px-6">
                <h2 className="text-3xl text-black font-bold tracking-tighter sm:text-5xl text-center mb-12">
                    Locales Asociados
                </h2>
                <div className="mx-auto max-w-4xl space-y-4 bg-slate-600 rounded-lg p-4">
                    <div id="map" style={{ height: "500px", width: "100%" }}></div>
                </div>
            </div>
        </section>
    );
};
