import { useState } from "react";
import Movie from "../types/Movie";
import Filter from "./Filter";
import Movies from "./Movies";
export default function Main() {
  const [moviesData, setMoviesData] = useState<Movie | null>(null);
  return (
    <div className="block md:flex mt-10 justify-center px-10">
      <div className="max-w-sm w-full md:text-start text-center ">
        <h1 className="text-2xl font-semibold font-serif">Filters</h1>
        <Filter setMoviesData={setMoviesData}/>
      </div>
      <div className="w-full max-w-3xl text-2xl font-semibold font-serif md:text-start text-center">
        <h1 className="text-2xl font-semibold font-serif mb-8">Movies</h1>
        <Movies moviesData={moviesData}/>
        
      </div>
    </div>
  );
}
