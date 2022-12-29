import axios from "axios";
import { useCallback, useEffect, useState } from "react";
type Movie = {
  title: string;
  description: string;
  imageUrl: string;
  releaseDate: string;
  genre: string[];
  rating: number;
  director: string;
  cast: string;
};

function Movies() {
  const [moviesData, setMoviesData] = useState<Movie | null>(null);
  useEffect(() => {
    async function fetchData() {
      const res = await axios.get(
        "http://localhost:8080/movies?type=search&movie=Inception"
      );
      setMoviesData(res.data);
    }

    fetchData();
    console.log(moviesData);
  }, [moviesData]);
  return (
    <>
      <div className="grid grid-cols-34 grid-flow-rows gap-2">
        {/* {Array.isArray(moviesData) &&
          moviesData?.map((movie) => ( */}
        {/* <div className="w-52 cursor-pointer" key={movie.title}>
              <img src={movie.imageUrl} className="object-fill" alt="" />
              <div className="px-1">
                <h2 className="text-black text-xl truncate mt-4">
                  {movie.title}
                </h2>
                <p className="text-sm font-thin truncate">
                  {movie.genre.join(", ")}
                </p> */}
        {/* <p className="text-sm font-thin truncate">{movie.language}</p> */}
        {/* </div>
            </div> */}
        {/* ))} */}
      </div>
    </>
  );
}

export default Movies;
