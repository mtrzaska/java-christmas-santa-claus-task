# Christmas Santa Claus's task

This Java program simulates the process of loading presents into Santa Claus's sleighs. It includes a set of warehouses that store presents, each with its weight, length, width, and height. The program randomly generates the number of warehouses, the presents inside them with their dimensions, and the sleighs along with their maximum dimensions and weight capacity.

## Functionality

- Generates a random number of warehouses and the presents within them.
- Generates a random number of sleighs and determines whether each sleigh driver has a 20% chance of being "big-boned," affecting the sleigh's maximum weight capacity.
- Loads the presents into the sleighs based on their capacity and dimensions.
- Provides output detailing the number of sleighs required to load all presents, or the number of presents that cannot be loaded due to insufficient sleigh capacity.
- Offers detailed information on presents that do not fit in the sleighs by warehouse.

## Features

- **Class `Present`:** Represents a gift with properties for weight and dimensions.
- **Class `Sleigh`:** Represents a sleigh with maximum dimensions and weight capacity. It includes logic to consider if the driver is "big-boned" and reduces the maximum weight capacity accordingly.
- **Class `Warehouse`:** Represents a warehouse that holds a list of presents.
- **Main Simulation (`SantaClausTask`):** The entry point for the simulation, handling the logic for generating warehouses, sleighs, and loading presents.

## Simulation Output

- Displays the dimensions and weight of each present in the warehouses.
- Indicates whether sleigh drivers are "big-boned" and their impact on the sleigh's capacity.
- Shows the total number of loaded presents and details of presents that cannot be loaded.

## How it Works

Upon execution, the program will:
1. Randomly generate warehouses and presents.
2. Randomly generate sleighs and determine the drivers' physique.
3. Attempt to load presents into the sleighs based on their capacity and present dimensions.
4. Output the loading results, including the number of sleighs used, the number of presents loaded, and the details of any presents that couldn't be loaded.

## Note

The presents cannot be divided into parts; if a present does not fit within the remaining capacity of the sleighs, it cannot be loaded. This simulator does not optimize the loading process but serves to illustrate the complexities involved in such an operation.
