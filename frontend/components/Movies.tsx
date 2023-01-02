import axios from "axios";
import { useEffect, useState } from "react";
import Movie from "../types/Movie";
interface MoviesProps {
  moviesData: Movie | null;
}
const Movies: React.FC<MoviesProps> = ({ moviesData }) => {
  if (moviesData == null) {
    return (
      <div className="flex justify-center items-center h-96">
        <p className="text-sm font-thin italic text-center">Movies Not Found</p>
      </div>
    );
  }
  return (
    <div className="md:block flex justify-center">
      <div className="grid grid-flow-rows gap-2 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
        {Array.isArray(moviesData) &&
          moviesData?.map((movie) => (
            <div className="w-52 cursor-pointer" key={movie.title}>
              <img src={movie.imageUrl} className="object-fill" alt="" />
              <div className="px-1">
                <h2 className="text-black text-xl truncate mt-4">
                  {movie.title}
                </h2>
                <p className="text-sm font-thin truncate">
                  {movie.genre.join(", ")}
                </p>
                <p className="text-sm font-thin truncate">{movie.language}</p>
              </div>
            </div>
          ))}
      </div>
    </div>
  );
};

export default Movies;
